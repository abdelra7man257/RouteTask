package com.example.routetask.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

@BindingAdapter("app:loadImageUrl")
fun imageLoading(image: ImageView, url: String?) {
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1800)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()
    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
    shimmerDrawable.startShimmer()
    Glide.with(image)
        .load(url)
        .placeholder(shimmerDrawable)
        .into(image)
}