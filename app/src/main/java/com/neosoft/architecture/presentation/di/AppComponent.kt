package com.neosoft.architecture.presentation.di

import com.neosoft.architecture.presentation.UserApplication
import com.neosoft.architecture.presentation.loginActivity.LoginActivity
import dagger.Component

/**
 * Created by Vijay on 27/2/19.
 */

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(loginActivity: LoginActivity)

}