package com.neosoft.architecture.presentation.navigation

import android.content.Context
import com.neosoft.architecture.presentation.view.SignInActivity

/**
 * Created by Vijay on 5/4/19.
 */


class Navigator {

    /*fun navigateToRegistrationActivity(context: Context) {
        context.startActivity(RegistrationActivity.getCallingIntent(context))
    }*/

    fun navigateToSignInActivity(context: Context) {
        context.startActivity(SignInActivity.getCallingIntent(context))
    }

    fun navigateToMapsActivity(context: Context) {
        context.startActivity(MapsActivity.getCallingIntent(context))
    }


}