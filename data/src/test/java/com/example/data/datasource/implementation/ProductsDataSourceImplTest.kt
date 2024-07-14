package com.example.data.datasource.implementation

import com.example.data.datasource.contracts.ProductsDataSource
import com.example.data.model.ProductsItemDto
import com.example.data.model.ProductsResponseDto
import com.example.data.webservice.WebServices
import com.example.domain.model.ProductsItem
import com.example.domain.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsDataSourceImplTest {

    private lateinit var productsDataSourceImpl: ProductsDataSource
    private val webServices = mockk<WebServices>()

    @Before
    fun setUp() {
        productsDataSourceImpl = ProductsDataSourceImpl(webServices)
    }

    @Test
    fun `when invoke get products data source function it should return products response`() =
        runTest {
            val productsResponse = ProductsResponseDto(products = listOf(ProductsItemDto()))

            coEvery { webServices.getProducts() } returns productsResponse

            val result = productsDataSourceImpl.getProducts()
                .last() as ResultWrapper.Success<List<ProductsItem?>?>

            assertEquals(1, result.data?.size)
        }


}