package com.neosoft.architecture.presentation.di.module

import android.app.Application
import android.content.Context
import com.neosoft.architecture.presentation.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vijay on 27/2/19.
 */

@Module
class ApplicationModule(application: Application) {

    var application: Application? = application

    @Provides
    @Singleton
    fun provideContext(): Context? {
        return application?.applicationContext
    }

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return Navigator()
    }


}