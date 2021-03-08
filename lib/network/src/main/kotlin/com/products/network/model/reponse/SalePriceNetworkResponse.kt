package com.products.network.model.reponse

import com.squareup.moshi.Json

data class SalePriceNetworkResponse(
    @field:Json(name = "amount") val amount: String = "",
    @field:Json(name = "currency") val currency: String = ""
)
