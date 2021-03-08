package com.products.data.datasource

import com.products.network.service.CatalogService
import javax.inject.Inject

class CatalogRemoteDataSource @Inject constructor(
    private val catalogService: CatalogService
) {

    suspend fun fetchCatalog() = catalogService.fetchCategories()
}
