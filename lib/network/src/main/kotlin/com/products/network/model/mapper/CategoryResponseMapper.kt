package com.products.network.model.mapper

import com.products.model.Category
import com.products.model.Product
import com.products.model.SalePrice
import com.products.network.model.reponse.CategoryNetworkResponse
import com.products.network.model.reponse.ProductNetworkResponse
import com.products.network.model.reponse.SalePriceNetworkResponse

fun List<CategoryNetworkResponse>.toCategories(): List<Category> {
    return this.map(CategoryNetworkResponse::toCategory)
}

fun List<ProductNetworkResponse>.toProducts(): List<Product> {
    return this.map(ProductNetworkResponse::toProduct)
}

fun CategoryNetworkResponse.toCategory(): Category {
    return Category(
        id = this.id,
        description = this.description,
        name = this.name,
        products = this.products.toProducts()
    )
}

fun SalePriceNetworkResponse.toSalePrice(): SalePrice {
    return SalePrice(
        amount = this.amount,
        currency = this.currency
    )
}

fun ProductNetworkResponse.toProduct(): Product {
    return Product(
        id = this.id,
        description = this.description,
        name = this.name,
        url = this.url,
        salePrice = this.salePrice?.toSalePrice()
    )
}
