package com.vinhdn.question2.helper.extension

import android.widget.Toast
import com.vinhdn.question2.app.App

//Extension to show toast
fun toast(message: Any, length: Int = Toast.LENGTH_LONG) {
    when (message) {
        is String -> Toast.makeText(App.shared(), message, length).show()
        is Int -> Toast.makeText(App.shared(), message, length).show()
        else -> throw IllegalArgumentException("Argument message type is invalid. The first argument is only accepted on Int or String")
    }
}