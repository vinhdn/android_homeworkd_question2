package com.vinhdn.question2.ui.product

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinhdn.question2.R
import com.vinhdn.question2.helper.extension.load
import com.vinhdn.question2.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(var listProduct: List<Product>): RecyclerView.Adapter<ProductAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = listProduct.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val product = listProduct[position]
        holder.itemView.tvTitle.text = product.productName
        holder.itemView.tvPrice.text = "$${product.price}"
        holder.itemView.ivCover.load(product.imageUrl)
        holder.itemView.setOnClickListener {  }
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view)
}