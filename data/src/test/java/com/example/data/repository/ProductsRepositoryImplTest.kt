package com.example.data.repository

import com.example.data.datasource.contracts.ProductsDataSource
import com.example.domain.model.ProductsItem
import com.example.domain.repository.ProductsRepository
import com.example.domain.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsRepositoryImplTest {

    lateinit var productsRepositoryImpl: ProductsRepository
    val productsDataSource = mockk<ProductsDataSource>()

    @Before
    fun setUp() {
        productsRepositoryImpl = ProductsRepositoryImpl(productsDataSource)
    }

    @Test
    fun `when invoke get products fun from repository it should emit loading`() = runTest {

        val response = ResultWrapper.Loading

        coEvery { productsDataSource.getProducts() } returns flowOf(response)
        val result = productsRepositoryImpl.getProducts()

        result.first() as ResultWrapper.Loading

    }


    @Test
    fun `when invoke get products fun from repository it should emit error`() = runTest {

        val response = ResultWrapper.Error("error")

        coEvery { productsDataSource.getProducts() } returns flowOf(response)
        val result = productsRepositoryImpl.getProducts()

        result.first() as ResultWrapper.Error
        assertEquals("error", (result.first() as ResultWrapper.Error).message)

    }

    @Test
    fun `when invoke get products fun from repository it should emit products response`() =
        runTest {

            val response = ResultWrapper.Success<List<ProductsItem>>(data = listOf(ProductsItem()))

            coEvery { productsDataSource.getProducts() } returns flowOf(response)
            val result = productsRepositoryImpl.getProducts()

            result.first() as ResultWrapper.Success<List<ProductsItem?>?>
            assertEquals(
                1,
                (result.first() as ResultWrapper.Success<List<ProductsItem?>?>).data?.size
            )

        }
}