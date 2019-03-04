package com.neosoft.architecture.presentation.di

import com.neosoft.architecture.presentation.loginActivity.LoginActivity
import dagger.Component

/**
 * Created by Vijay on 27/2/19.
 */

@Component(modules = arrayOf(LoginModule::class), dependencies = arrayOf(AppComponent::class))
interface LoginComponent {

    fun inject(loginActivity: LoginActivity)

}