package com.example.data.datasource.implementation

import com.example.data.datasource.contracts.ProductsDataSource
import com.example.data.utils.safeApiCall
import com.example.data.webservice.WebServices
import com.example.domain.model.ProductsItem
import com.example.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    ProductsDataSource {
    override suspend fun getProducts(limit: Int?): Flow<ResultWrapper<List<ProductsItem?>?>> {
        return safeApiCall {
            webServices.getProducts(limit)?.products?.map {
                it?.toProductItem()
            }
        }
    }

}