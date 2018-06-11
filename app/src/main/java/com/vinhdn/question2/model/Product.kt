package com.vinhdn.question2.model

import com.google.gson.annotations.SerializedName

data class Product(var productId: String,
                   var productName: String,
                   var imageUrl: String,
                   var price: String)

class ProductResponse : APIResponse<List<Product>>() {
    @SerializedName("products")
    override var data: List<Product>? = null
}