package com.products.test.data.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.products.data.datasource.CatalogRemoteDataSource
import com.products.data.repository.CatalogRepository
import com.products.network.model.NetworkResponse
import com.products.network.model.mapper.RepositoryResult
import com.products.network.model.reponse.CategoryNetworkResponse
import com.products.network.model.reponse.ProductNetworkResponse
import com.products.network.model.reponse.SalePriceNetworkResponse
import com.products.network.service.CatalogService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CatalogRepositoryTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    @MockK(relaxed = true)
    lateinit var catalogService: CatalogService
    private lateinit var catalogRepository: CatalogRepository
    private lateinit var catalogRemoteDataSource: CatalogRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        catalogService = mock()
        catalogRemoteDataSource = CatalogRemoteDataSource(catalogService)
        catalogRepository = CatalogRepository(catalogRemoteDataSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        dispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun getCatalogSuccess() {
        testScope.launch {
            val categoryNetworkList = listOf(
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

            whenever(catalogService.fetchCategories())
                .thenReturn(NetworkResponse.Success(categoryNetworkList))
            val response = catalogRepository.fetchCatalog()
            assertNotNull(response)
            assert(response is RepositoryResult.Success)
        }
    }

    @Test
    fun getCatalogApiError() {
        testScope.launch {
            whenever(catalogService.fetchCategories())
                .thenReturn(NetworkResponse.ApiError("Error", 400))
            val response = catalogRepository.fetchCatalog()
            assertNotNull(response)
            assert(response is RepositoryResult.Fail<*>)
        }
    }

    @Test
    fun getCatalogNetworkError() {
        testScope.launch {
            whenever(catalogService.fetchCategories())
                .thenReturn(NetworkResponse.NetworkError(IOException()))
            val response = catalogRepository.fetchCatalog()
            assertNotNull(response)
            assert(response is RepositoryResult.Exception)
        }
    }
}
