package com.products.test.network.services

import com.products.network.model.mapper.toRepositoryResult
import com.products.network.service.CatalogService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MockResponses {
    object GetCatalogInfo {
        const val STATUS_200 = "mock-responses/get-catalog-status200.json"
        const val STATUS_404 = "mock-responses/get-catalog-status404.json"
    }
}

@ExperimentalCoroutinesApi
class BitcoinServiceServiceTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    private lateinit var service: CatalogService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatalogService::class.java)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        dispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun responseCatalogCode200() {
        enqueueResponse(MockResponses.GetCatalogInfo.STATUS_200)
        testScope.launch {
            val response = service.fetchCategories()
            assertEquals(
                "36802",
                response.toRepositoryResult { it.first().id }
            )
        }
    }

    @Test
    fun responseCatalogCode400() {
        enqueueResponse(MockResponses.GetCatalogInfo.STATUS_404)
        testScope.launch {
            val response = service.fetchCategories()
            assertEquals("not-found", response)
            assertNull(response)
        }
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
