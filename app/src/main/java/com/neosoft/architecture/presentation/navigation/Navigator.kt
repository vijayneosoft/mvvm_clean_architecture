package com.neosoft.architecture.presentation.navigation

import android.content.Context
import com.neosoft.architecture.presentation.view.SignInActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vijay on 5/4/19.
 */

@Singleton
class Navigator {

    /*fun navigateToRegistrationActivity(application: Context) {
        application.startActivity(RegistrationActivity.getCallingIntent(application))
    }*/
    @Inject
    fun navigateToSignInActivity(context: Context) {
        context.startActivity(SignInActivity.getCallingIntent(context))
    }

   /* fun navigateToMapsActivity(application: Context) {
        application.startActivity(MapsActivity.getCallingIntent(application))
    }*/


}