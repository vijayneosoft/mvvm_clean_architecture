package com.neosoft.architecture.presentation.loginActivity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.neosoft.architecture.data.netCall.RestApi
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

    var restApi: RestApi? = null

    constructor(mLoginUC: LoginUC?) {
        this.mLoginUC = mLoginUC
    }

    fun loginResponse(): LiveData<LoginModel> {
        return mMutableLiveData
    }

    fun doLoginVM(username: String, password: String) {

        mDisposables.add(mLoginUC!!.doLoginUC(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe({ d -> mMutableLiveData.setValue(LoginModel.loading()) })
            .subscribe(
                { result -> mMutableLiveData.setValue(LoginModel.success(result)) },
                { throwable -> mMutableLiveData.setValue(LoginModel.error(throwable)) }
            ))
    }




}