package com.neosoft.architecture.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.neosoft.architecture.domain.ctx.emailAuth.EmailAuthListener
import com.neosoft.architecture.domain.usecases.LoginUC
import com.neosoft.architecture.presentation.model.RegistrationModel

/**
 * Created by Vijay on 7/3/19.
 */

class RegistrationViewModel : ViewModel, EmailAuthListener {

    var mLoginUC: LoginUC? = null
    var mMutableLiveData = MutableLiveData<RegistrationModel>()

    constructor(mLoginUC: LoginUC) {
        this.mLoginUC = mLoginUC
    }

    fun registrationResponse(): MutableLiveData<RegistrationModel> {
        return mMutableLiveData
    }

    fun registerUser(email: String, password: String) {
        mMutableLiveData.setValue(RegistrationModel.loading())
        mLoginUC?.emailVerification(email, password, this)
    }

    override fun onSuccess(user: FirebaseUser) {
        mMutableLiveData.setValue(RegistrationModel.success(user))
    }

    override fun onFailure(exception: Exception?) {
        mMutableLiveData.setValue(RegistrationModel.error(exception))
    }


}