package com.neosoft.architecture.presentation.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neosoft.architecture.domain.usecases.LoginUC
import com.neosoft.architecture.domain.usecases.NetworkingUC
import com.neosoft.architecture.presentation.viewmodel.RegistrationViewModel
import com.neosoft.architecture.presentation.viewmodel.SignInViewModel
import javax.inject.Inject

/**
 * Created by Vijay on 4/3/19.
 */

class ViewModelFactory
@Inject
constructor(val networkingUC: NetworkingUC, val mloginUC: LoginUC) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(networkingUC) as T
        }
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(mloginUC) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
