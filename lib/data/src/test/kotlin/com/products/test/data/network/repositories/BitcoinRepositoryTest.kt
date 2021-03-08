package com.products.data.network.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.products.data.network.repositiories.BitcoinRepository
import com.products.data.network.repositiories.datasource.NetworkDataSource
import com.products.data.network.services.BitcoinService
import com.products.data.network.utils.RequestConstants.ROLLING_AVERAGE
import com.products.data.network.utils.RequestConstants.TIME_SPAN
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BitcoinRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var bitcoinService: BitcoinService
    private lateinit var bitcoinRepository: BitcoinRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        bitcoinRepository = NetworkDataSource(bitcoinService)
    }

    @Test
    fun getBitcoinPricesList() {
        val timeSpanMocked = TIME_SPAN
        val rollingAverageMocked = ROLLING_AVERAGE
        val timestamp = slot<String>()
        val rollingAverage = slot<String>()

        bitcoinRepository.getBitCoinPriceInTime(
            timeSpanMocked
        )

        coVerify {
            bitcoinService.getBitCoinPrice(
                capture(timestamp),
                capture(rollingAverage)
            )
        }

        assertEquals(timeSpanMocked, timestamp.captured)
        assertNotNull(timestamp.captured)
        assertEquals(rollingAverageMocked, rollingAverage.captured)
        assertNotNull(rollingAverage.captured)
    }
}
