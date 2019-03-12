package com.neosoft.architecture.presentation

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.google.firebase.FirebaseApp
import com.neosoft.architecture.presentation.di.component.AppComponent
import com.neosoft.architecture.presentation.di.module.AppModule
import com.neosoft.architecture.presentation.di.component.DaggerAppComponent


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

        FirebaseApp.initializeApp(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }

    fun getComponent(): AppComponent? {
        return appComponent
    }


}