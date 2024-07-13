package com.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewsItem(
    val date: String? = null,
    val reviewerName: String? = null,
    val reviewerEmail: String? = null,
    val rating: Int? = null,
    val comment: String? = null
) : Parcelable