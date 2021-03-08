package com.products.data.repository

import com.products.data.datasource.CatalogRemoteDataSource
import com.products.model.Category
import com.products.network.model.mapper.RepositoryResult
import com.products.network.model.mapper.toCategories
import com.products.network.model.mapper.toRepositoryResult
import com.products.network.model.reponse.CategoryNetworkResponse
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    private val catalogRemoteDataSource: CatalogRemoteDataSource,
) {

    suspend fun fetchCatalog(): RepositoryResult<List<Category>> =
        catalogRemoteDataSource.fetchCatalog()
            .toRepositoryResult(List<CategoryNetworkResponse>::toCategories)
}
