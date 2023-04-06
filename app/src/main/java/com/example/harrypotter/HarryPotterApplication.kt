package com.example.harrypotter

import android.app.Application
import com.example.harrypotter.data.AppContainer
import com.example.harrypotter.data.DefaultAppContainer

class HarryPotterApplication : Application() {

    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}