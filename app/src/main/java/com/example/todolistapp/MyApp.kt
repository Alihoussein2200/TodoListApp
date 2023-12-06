package com.example.todolistapp

import android.app.Application
import com.example.todolistapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.giphy.sdk.ui.Giphy
import com.giphy.sdk.ui.views.GiphyDialogFragment

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
            Giphy.configure(this, "yV80d1qjoaiPW1y8kdigkRlaWPMSlyg9")



    }
}
