package com.products.data.usecase

import com.products.data.repository.CatalogRepository
import javax.inject.Inject

class FetchCatalogUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {

    suspend fun fetchCatalog() = catalogRepository.fetchCatalog()
}
