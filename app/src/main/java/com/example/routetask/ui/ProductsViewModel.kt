package com.example.routetask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProductsItem
import com.example.domain.usecase.GetProductsUseCase
import com.example.domain.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase) :
    ViewModel() {
    val response = MutableStateFlow<ResultWrapper<List<ProductsItem?>?>>(ResultWrapper.Loading)

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.invoke().collect {
                when (it) {
                    is ResultWrapper.Error -> response.emit(ResultWrapper.Error(it.message))
                    is ResultWrapper.Loading -> response.emit(ResultWrapper.Loading)
                    is ResultWrapper.Success -> response.emit(ResultWrapper.Success(it.data))
                }
            }
        }
    }

}