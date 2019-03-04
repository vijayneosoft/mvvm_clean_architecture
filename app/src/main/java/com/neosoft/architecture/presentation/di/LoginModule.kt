package com.neosoft.architecture.presentation.di

import android.content.Context
import dagger.Module

/**
 * Created by Vijay on 27/2/19.
 */

@Module
class LoginModule {

    var context: Context? = null

    constructor(context: Context) {
        this.context = context
    }

}