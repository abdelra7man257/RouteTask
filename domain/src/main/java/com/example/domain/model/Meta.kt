package com.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meta(
    val createdAt: String? = null,
    val qrCode: String? = null,
    val barcode: String? = null,
    val updatedAt: String? = null
) : Parcelable