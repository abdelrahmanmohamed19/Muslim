package com.example.muslim.presentation.ui

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NetworkUtils.initialize(this)
    }
}