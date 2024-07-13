package com.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dimensions(
    val depth: Double? = null,
    val width: Double? = null,
    val height: Double? = null
) : Parcelable