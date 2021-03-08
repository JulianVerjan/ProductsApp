package com.products.network.model.reponse

import com.squareup.moshi.Json

data class CategoryNetworkResponse(
    val id: String = "",
    @field:Json(name = "name") val name: String = "",
    @field:Json(name = "description") val description: String = "",
    @field:Json(name = "products") val products: List<ProductNetworkResponse> = listOf()
)
