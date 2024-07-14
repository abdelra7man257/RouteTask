package com.example.domain.repository

import com.example.domain.model.ProductsItem
import com.example.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(limit: Int? = 30): Flow<ResultWrapper<List<ProductsItem?>?>>
}