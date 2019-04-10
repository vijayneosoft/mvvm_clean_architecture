package com.neosoft.architecture.presentation.di.module

import android.content.Context
import com.neosoft.architecture.presentation.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Vijay on 27/2/19.
 */

@Module
class AppModule {

    var context: Context? = null

    constructor(context: Context) {
        this.context = context
    }

    @Provides
    @Singleton
    fun provideContext(): Context? {
        return context
    }

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return Navigator()
    }


}