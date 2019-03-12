package com.neosoft.architecture.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.neosoft.architecture.domain.usecases.NetworkingUC
import com.neosoft.architecture.presentation.ui.model.SignInModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Vijay on 26/2/19.
 */

class SignInViewModel(var mNetworkingUC: NetworkingUC?) : ViewModel() {

    var mDisposables = CompositeDisposable()
    var mMutableLiveData = MutableLiveData<SignInModel>()

    fun loginResponse(): LiveData<SignInModel> {
        return mMutableLiveData
    }

    fun doLoginVM(username: String, password: String) {
        mDisposables.add(mNetworkingUC!!.doSignInUC(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe({ d -> mMutableLiveData.setValue(SignInModel.loading()) })
            .subscribe(
                { result -> mMutableLiveData.setValue(SignInModel.success(result)) },
                { throwable -> mMutableLiveData.setValue(SignInModel.error(throwable)) }
            )
        )
    }


}