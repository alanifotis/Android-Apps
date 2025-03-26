package com.example.mywordle

import android.app.Application
import com.example.mywordle.data.AppContainer
import com.example.mywordle.data.AppDataContainer

class WordApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
