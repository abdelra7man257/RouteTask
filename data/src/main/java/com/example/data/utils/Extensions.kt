package com.example.data.utils


fun Double.calculatePriceDisCount(discountPercentage: Double): String {
    val percentage = discountPercentage.div(100)
    val discountValue = this.times(percentage)
    val result = this.minus(discountValue)
    return String.format("%.1f", result)
}