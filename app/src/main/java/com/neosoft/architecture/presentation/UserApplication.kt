package com.neosoft.architecture.presentation

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.neosoft.architecture.presentation.di.AppComponent
import com.neosoft.architecture.presentation.di.AppModule
import com.neosoft.architecture.presentation.di.DaggerAppComponent


/**
 * Created by Vijay on 26/2/19.
 */

class UserApplication : Application() {

    var appComponent: AppComponent? = null
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = this
        Stetho.initializeWithDefaults(this);

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    }

    fun getComponent() : AppComponent? {
        return appComponent
    }


}