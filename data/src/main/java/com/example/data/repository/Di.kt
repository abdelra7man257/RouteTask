package com.example.data.repository

import com.example.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class Di {

    @Binds
    abstract fun bindProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository
}