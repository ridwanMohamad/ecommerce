package com.mridwan.ecommerce

import android.app.Application

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: Application
    }
}