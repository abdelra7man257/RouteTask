package com.example.routetask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProductsItem
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.utils.ResultWrapper
import com.example.routetask.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {
    val response = MutableStateFlow<ResultWrapper<List<ProductsItem?>?>>(ResultWrapper.Loading)

    init {
        getProducts()
    }

    private fun getProducts(limit: Int? = 30) {
        viewModelScope.launch(ioDispatcher) {
            getProductsUseCase.invoke(limit).collect {
                when (it) {
                    is ResultWrapper.Error -> response.emit(ResultWrapper.Error(it.message))
                    is ResultWrapper.Loading -> response.emit(ResultWrapper.Loading)
                    is ResultWrapper.Success -> response.emit(ResultWrapper.Success(it.data))
                }
            }
        }
    }

}