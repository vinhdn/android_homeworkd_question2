package com.vinhdn.question2.app

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
        fun shared(): App {
            return instance
        }
    }
}