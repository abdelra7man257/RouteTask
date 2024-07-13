package com.example.data.datasource.di

import com.example.data.datasource.contracts.ProductsDataSource
import com.example.data.datasource.implementation.ProductsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {

    @Binds
    abstract fun bindProductsDataSource(getProductsDataSourceImpl: ProductsDataSourceImpl): ProductsDataSource
}