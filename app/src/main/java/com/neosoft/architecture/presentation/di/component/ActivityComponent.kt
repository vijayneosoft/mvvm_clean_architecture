package com.neosoft.architecture.presentation.di.component

import com.neosoft.architecture.presentation.di.PerActivity
import com.neosoft.architecture.presentation.di.module.ActivityModule
import com.neosoft.architecture.presentation.view.RegistrationActivity
import com.neosoft.architecture.presentation.view.SignInActivity
import dagger.Component

/**
 * Created by Vijay on 11/4/19.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(registrationActivity: RegistrationActivity)

    fun inject(signInActivity: SignInActivity)

}
