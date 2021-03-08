package com.com.catalog

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.products.catalog.catalog.CatalogViewModel
import com.products.catalog.model.UIState
import com.products.data.usecase.FetchCatalogUseCase
import com.products.model.Category
import com.products.model.Product
import com.products.model.SalePrice
import com.products.network.model.mapper.RepositoryResult
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

    private val categoryList = listOf(
        Category(
            "424", "food", "test",
            listOf(
                Product(
                    "343", "424",
                    "bread", "dfgdf.png",
                    "testProduct",
                    SalePrice("23", "EUR")
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
            whenever(fetchCatalogUseCase.fetchCatalog())
                .thenReturn(RepositoryResult.Success(categoryList))

            catalogViewModel.fetchCatalog()
            catalogViewModel.viewStateFlow.collect {
                assertNotNull(it.result)
                assertEquals(it.result.size, 1)
                assertEquals(it.result.first().id, "424")
            }
        }
    }

    @Test
    fun fetchCatalogSuccessfullyWithEmptyView() {
        testScope.launch {
            whenever(fetchCatalogUseCase.fetchCatalog())
                .thenReturn(RepositoryResult.Success(emptyList()))

            catalogViewModel.fetchCatalog()
            catalogViewModel.viewStateFlow.collect {
                assertNotNull(it.result)
                assertEquals(it.result.size, 0)
            }
        }
    }

    @Test
    fun fetchCatalogWithError() {
        testScope.launch {
            whenever(fetchCatalogUseCase.fetchCatalog())
                .thenReturn(RepositoryResult.Fail("error"))

            catalogViewModel.fetchCatalog()
            catalogViewModel.viewStateFlow.collect {
                assertNotNull(it.result)
                assertEquals(it.uiState, UIState.ERROR)
            }
        }
    }

    @Test
    fun fetchCatalogWithApiError() {
        testScope.launch {
            whenever(fetchCatalogUseCase.fetchCatalog())
                .thenReturn(RepositoryResult.Exception(Exception()))

            catalogViewModel.fetchCatalog()
            catalogViewModel.viewStateFlow.collect {
                assertNotNull(it.result)
                assertEquals(it.uiState, UIState.ERROR)
            }
        }
    }
}
