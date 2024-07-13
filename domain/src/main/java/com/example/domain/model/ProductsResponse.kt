package com.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsResponse(
    val total: Int? = null,
    val limit: Int? = null,
    val skip: Int? = null,
    val products: List<ProductsItem?>? = null
) : Parcelable