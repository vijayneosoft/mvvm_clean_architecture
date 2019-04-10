package com.neosoft.architecture.presentation.di.component

import com.neosoft.architecture.presentation.di.module.AppModule
import com.neosoft.architecture.presentation.di.module.DataModule
import com.neosoft.architecture.presentation.di.module.LoginModule
import com.neosoft.architecture.presentation.di.module.NetworkingModule
import com.neosoft.architecture.presentation.navigation.Navigator
import com.neosoft.architecture.presentation.view.RegistrationActivity
import com.neosoft.architecture.presentation.view.SignInActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Vijay on 27/2/19.
 */

@Component(modules = arrayOf(AppModule::class, LoginModule::class, NetworkingModule::class, DataModule::class))
@Singleton
interface AppComponent {

    fun inject(registrationActivity: RegistrationActivity)

    fun inject(signInActivity: SignInActivity)

    fun navigator(): Navigator

}