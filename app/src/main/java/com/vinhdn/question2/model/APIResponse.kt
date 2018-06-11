package com.vinhdn.question2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class APIResponse<T> {
    var status: String = ""
    var errorMessage: String = "Have an error"
    public open var data: T? = null
}