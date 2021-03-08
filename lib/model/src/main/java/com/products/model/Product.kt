package com.products.model

import java.io.Serializable

data class Product(
    val id: String = "",
    val categoryId: String = "",
    val name: String = "",
    val url: String = "",
    val description: String = "",
    val salePrice: SalePrice? = null
) : Serializable
