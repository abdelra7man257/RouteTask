package com.example.routetask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ProductsItem
import com.example.routetask.databinding.ProductItemBinding
import com.example.routetask.utils.DiffUtils

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var productsList: MutableList<ProductsItem?>? = mutableListOf()

    class ViewHolder(val item: ProductItemBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int = productsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        productsList?.getOrNull(position)?.let { item ->
            holder.item.item = item
            holder.itemView.invalidate()
        }
    }

    fun setProductsList(productsList: List<ProductsItem?>?) {
        val diffCallback = DiffUtils(this.productsList, productsList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.productsList?.clear()
        this.productsList?.addAll(productsList ?: listOf())
        diffResult.dispatchUpdatesTo(this)
    }


}