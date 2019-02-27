package com.neosoft.architecture.presentation.loginActivity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.neosoft.architecture.domain.usecases.LoginUC
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Vijay on 26/2/19.
 */

class LoginViewModel : ViewModel {

    var mDisposables = CompositeDisposable()
    var mMutableLiveData = MutableLiveData<LoginModel>()

    var mLoginUC: LoginUC? = null

    constructor() {
        mLoginUC = LoginUC()
    }

    fun loginResponse(): LiveData<LoginModel> {
        return mMutableLiveData
    }

    fun doLoginVM(username: String, password: String) {

        mDisposables.add(mLoginUC!!.doLoginUC(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe({ mMutableLiveData.value = LoginModel.loading() })
            .subscribe(
                { result -> mMutableLiveData.value = LoginModel.success(result) },
                { throwable -> mMutableLiveData.value = LoginModel.error(throwable) }
            ))
    }


}