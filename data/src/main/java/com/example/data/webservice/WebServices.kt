package com.example.data.webservice

import com.example.data.model.ProductsResponseDto
import com.example.data.utils.ApiConstants.PRODUCTS_END_POINT
import retrofit2.http.GET

interface WebServices {
    @GET(PRODUCTS_END_POINT)
    suspend fun getProducts(): ProductsResponseDto?
}