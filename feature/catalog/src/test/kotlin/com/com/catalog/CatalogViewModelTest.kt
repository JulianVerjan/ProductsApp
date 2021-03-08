package com.com.catalog

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.products.catalog.catalog.CatalogViewModel
import com.products.data.usecase.FetchCatalogUseCase
import com.products.network.model.NetworkResponse
import com.products.network.model.reponse.CategoryNetworkResponse
import com.products.network.model.reponse.ProductNetworkResponse
import com.products.network.model.reponse.SalePriceNetworkResponse
import com.products.network.service.CatalogService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CatalogViewModelTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)
    private var fetchCatalogUseCase = mock<FetchCatalogUseCase>()
    private var catalogViewModel = CatalogViewModel(fetchCatalogUseCase)
    private lateinit var catalogService: CatalogService

    private val categoryNetworkList = listOf(
        CategoryNetworkResponse(
            "424", "food", "test",
            listOf(
                ProductNetworkResponse(
                    "343", "424",
                    "bread", "dfgdf.png",
                    "testProduct",
                    SalePriceNetworkResponse("23", "EUR")
                )
            )
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        catalogService = mock()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        dispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun fetchCatalogSuccessfully() {
        testScope.launch {
            whenever(catalogService.fetchCategories())
                .thenReturn(NetworkResponse.Success(categoryNetworkList))
            catalogViewModel.fetchCatalog()
            catalogViewModel.viewStateFlow.collect {
                assertNotNull(it.result)
                assertEquals(it.result.size, 1)
                assertEquals(it.result.first().id, "424")
            }
        }
    }
}
