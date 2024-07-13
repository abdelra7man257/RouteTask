package com.example.data.model

import com.example.domain.model.ProductsResponse
import com.google.gson.annotations.SerializedName

data class ProductsResponseDto(

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("limit")
    val limit: Int? = null,

    @field:SerializedName("skip")
    val skip: Int? = null,

    @field:SerializedName("products")
    val products: List<ProductsItemDto?>? = null
) {
    fun toProductsResponse(): ProductsResponse {
        return ProductsResponse(
            total, limit, skip, products = products?.map { it?.toProductItem() }
        )
    }
}