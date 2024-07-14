package com.example.data.datasource.contracts

import com.example.domain.model.ProductsItem
import com.example.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ProductsDataSource {

    suspend fun getProducts(limit: Int? = 30): Flow<ResultWrapper<List<ProductsItem?>?>>
}