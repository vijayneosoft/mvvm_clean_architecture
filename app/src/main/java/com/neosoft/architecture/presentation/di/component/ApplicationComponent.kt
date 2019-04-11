package com.neosoft.architecture.presentation.di.component

import com.neosoft.architecture.data.net.RestApi
import com.neosoft.architecture.domain.ctx.emailAuth.EmailAuthProvider
import com.neosoft.architecture.domain.usecases.LoginUC
import com.neosoft.architecture.domain.usecases.NetworkingUC
import com.neosoft.architecture.presentation.di.module.ApplicationModule
import com.neosoft.architecture.presentation.di.module.DataModule
import com.neosoft.architecture.presentation.navigation.Navigator
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Vijay on 27/2/19.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface ApplicationComponent {

    fun navigator(): Navigator

    fun restApi(): RestApi

    fun emailAuthProvider(): EmailAuthProvider


}