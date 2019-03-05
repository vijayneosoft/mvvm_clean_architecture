package com.neosoft.architecture.presentation.ui.viewModelFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.neosoft.architecture.domain.usecases.LoginUC
import com.neosoft.architecture.presentation.ui.viewmodel.LoginViewModel
import javax.inject.Inject

/**
 * Created by Vijay on 4/3/19.
 */

class ViewModelFactory
@Inject
constructor(val loginUC: LoginUC) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginUC) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
