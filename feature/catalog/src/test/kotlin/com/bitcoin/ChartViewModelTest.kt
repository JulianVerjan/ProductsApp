package com.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.products.catalog.ChartViewModel
import com.products.catalog.state.ViewResult
import com.products.catalog.usecase.GetBlockChainInfoUseCase
import com.products.data.network.model.BlockChainResponse
import com.products.data.network.model.PriceResponse
import com.products.data.network.utils.RequestConstants.TIME_SPAN
import org.junit.Rule
import org.junit.Test
import org.mockito.Captor

class ChartViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getBlockChainInfoUseCase = mock<GetBlockChainInfoUseCase>()
    private var chartViewModel = ChartViewModel(getBlockChainInfoUseCase)

    @Captor
    private val captor = argumentCaptor<((BlockChainResponse) -> Unit)>()

    @Captor
    private val captorError = argumentCaptor<((Throwable) -> Unit)>()

    @Test
    fun getBitcoinPrices() {
        chartViewModel.loadBitcoinPrice(TIME_SPAN)
        verify(getBlockChainInfoUseCase, times(1))
            .invoke(any(), any(), any())
    }

    @Test
    fun fetchBitcoinPricesListSuccessfully() {
        val mockResponse = BlockChainResponse(
            "ok", "Hello name",
            "US", "day",
            "Test bitcoin bla bla",
            listOf(PriceResponse(23, 56.00))
        )

        chartViewModel.loadBitcoinPrice(TIME_SPAN)

        verify(getBlockChainInfoUseCase)
            .invoke(
                any(), captor.capture(), any()
            )
        captor.firstValue.invoke(mockResponse)

        assert(
            chartViewModel.blockChainIfo.value is ViewResult.Success
        )
    }

    @Test
    fun fetchBitcoinPricesEmptyListState() {
        val mockResponse = BlockChainResponse(
            "ok", "Hello name",
            "US", "day",
            "Test bitcoin bla bla",
            emptyList()
        )

        chartViewModel.loadBitcoinPrice(TIME_SPAN)

        verify(getBlockChainInfoUseCase)
            .invoke(
                any(), captor.capture(), any()
            )
        captor.firstValue.invoke(mockResponse)

        val infoResult = chartViewModel.blockChainIfo.value as ViewResult.Success
        assert(
            infoResult.data.isEmpty()
        )
    }

    @Test
    fun fetchBitcoinPricesErrorState() {

        chartViewModel.loadBitcoinPrice(TIME_SPAN)

        verify(getBlockChainInfoUseCase)
            .invoke(
                any(), any(), captorError.capture()
            )
        captorError.firstValue.invoke(Exception())

        assert(
            chartViewModel.blockChainIfo.value is ViewResult.ErrorState
        )
    }
}
