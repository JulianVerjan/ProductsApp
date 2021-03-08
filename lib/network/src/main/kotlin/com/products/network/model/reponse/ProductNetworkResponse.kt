package com.products.network.model.reponse

import com.squareup.moshi.Json

data class ProductNetworkResponse(
    val id: String = "",
    @field:Json(name = "categoryId") val categoryId: String = "",
    @field:Json(name = "name") val name: String = "",
    @field:Json(name = "url") val url: String = "",
    @field:Json(name = "description") val description: String = "",
    @field:Json(name = "salePrice") val salePrice: SalePriceNetworkResponse? = null
)
