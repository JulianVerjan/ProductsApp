package com.products.network.service

import com.products.network.model.NetworkResponse
import com.products.network.model.reponse.CategoryNetworkResponse
import retrofit2.http.GET

interface CatalogService {

    @GET("./")
    suspend fun fetchCategories(): NetworkResponse<List<CategoryNetworkResponse>, Any>
}
