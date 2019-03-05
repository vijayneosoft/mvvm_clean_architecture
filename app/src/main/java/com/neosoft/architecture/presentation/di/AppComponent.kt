package com.neosoft.architecture.presentation.di

import com.neosoft.architecture.presentation.ui.view.LoginActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Vijay on 27/2/19.
 */

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {

    fun inject(loginActivity: LoginActivity)

}