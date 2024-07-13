package com.example.data.repository

import com.example.data.datasource.contracts.ProductsDataSource
import com.example.domain.model.ProductsItem
import com.example.domain.repository.ProductsRepository
import com.example.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsDataSource: ProductsDataSource

) : ProductsRepository {
    override suspend fun getProducts(): Flow<ResultWrapper<List<ProductsItem?>?>> {
        return productsDataSource.getProducts()
    }

}