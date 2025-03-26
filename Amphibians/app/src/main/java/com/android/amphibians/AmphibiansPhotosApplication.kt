package com.android.amphibians

import android.app.Application
import com.android.amphibians.data.AppContainer
import com.android.amphibians.data.DefaultAppContainer

class AmphibiansPhotosApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}