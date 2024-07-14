package com.example.routetask.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.ProductsItem

class DiffUtils(
    private val oldList: List<ProductsItem?>?,
    private val newList: List<ProductsItem?>?
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.javaClass == newList?.get(newItemPosition)?.javaClass
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList?.get(oldItemPosition)
        val newItem = newList?.get(newItemPosition)

        return oldItem.hashCode() == newItem.hashCode()
    }
}