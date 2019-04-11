package com.neosoft.architecture.presentation

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.google.firebase.FirebaseApp
import com.neosoft.architecture.presentation.di.component.ApplicationComponent
import com.neosoft.architecture.presentation.di.component.DaggerApplicationComponent
import com.neosoft.architecture.presentation.di.module.ApplicationModule


/**
 * Created by Vijay on 26/2/19.
 */

class UserApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = this
        Stetho.initializeWithDefaults(this);

        FirebaseApp.initializeApp(this)

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

    }

    fun getComponent(): ApplicationComponent? {
        return applicationComponent
    }


}