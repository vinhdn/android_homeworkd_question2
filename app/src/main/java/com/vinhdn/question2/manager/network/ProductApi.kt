package com.vinhdn.question2.manager.network

import com.vinhdn.question2.model.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ProductApi {
    @GET("/api/product/list")
    fun getListProduct(): Single<ProductResponse>
}