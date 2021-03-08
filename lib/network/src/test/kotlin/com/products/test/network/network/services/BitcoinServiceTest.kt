package com.products.network.network.services

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.products.data.network.model.BlockChainResponse
import com.products.data.network.utils.RequestConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MockResponses {
    object GetBitcoinPricesId {
        const val STATUS_200 = "mock-responses/get-bitcoin-prices-status200.json"
        const val STATUS_404 = "mock-responses/get-bitcoin-prices-status404.json"
    }
}

class BitcoinServiceServiceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var service: BitcoinApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(BitcoinApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestGetBitcoinPricesList() {
        val timeSpanMocked = RequestConstants.TIME_SPAN
        val rollingAverageMocked = RequestConstants.ROLLING_AVERAGE
        enqueueResponse(MockResponses.GetBitcoinPricesId.STATUS_200)
        val apiObserver = TestObserver<BlockChainResponse>()
        // send request
        service.getBitCoinPrice(timeSpanMocked, rollingAverageMocked)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(apiObserver)

        apiObserver.awaitTerminalEvent(1, TimeUnit.SECONDS)
        apiObserver.assertNoErrors()
    }

    @Test
    fun responseGetBitcoinPriceListCode200() {
        val timeSpanMocked = RequestConstants.TIME_SPAN
        val rollingAverageMocked = RequestConstants.ROLLING_AVERAGE
        enqueueResponse(MockResponses.GetBitcoinPricesId.STATUS_200)

        val response = service.getBitCoinPrice(timeSpanMocked, rollingAverageMocked).blockingGet()
        assertEquals(
            "Average USD market price across major bitcoin exchanges.",
            response.description
        )
        assertEquals("ok", response.status)
        assertEquals(response.values.size, 30)
    }

    @Test
    fun responseGetBitcoinPriceListCode400() {
        val timeSpanMocked = RequestConstants.TIME_SPAN
        val rollingAverageMocked = RequestConstants.ROLLING_AVERAGE
        enqueueResponse(MockResponses.GetBitcoinPricesId.STATUS_404)
        val response = service.getBitCoinPrice(
            timeSpanMocked,
            rollingAverageMocked
        ).blockingGet()

        assertEquals("not-found", response.status)
        assertNull(response.values)
    }

    private fun enqueueResponse(filePath: String) {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val bufferSource = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse.setBody(
                bufferSource!!.readString(Charsets.UTF_8)
            )
        )
    }
}
