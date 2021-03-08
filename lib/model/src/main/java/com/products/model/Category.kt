package com.products.model

import java.io.Serializable

data class Category(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val products: List<Product> = listOf()
) : Serializable
