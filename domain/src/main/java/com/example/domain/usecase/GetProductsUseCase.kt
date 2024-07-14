package com.example.domain.usecase

import com.example.domain.model.ProductsItem
import com.example.domain.repository.ProductsRepository
import com.example.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productsRepository: ProductsRepository) {

    suspend fun invoke(limit: Int? = 10): Flow<ResultWrapper<List<ProductsItem?>?>> {
        return productsRepository.getProducts(limit)
    }
}