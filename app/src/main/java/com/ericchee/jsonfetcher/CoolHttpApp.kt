package com.ericchee.jsonfetcher

import android.app.Application
import android.content.Context

class CoolHttpApp: Application() {

    lateinit var apiManager: ApiManager

    override fun onCreate() {
        super.onCreate()

        // Load managers
        apiManager = ApiManager(this)

    }
}