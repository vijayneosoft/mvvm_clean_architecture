package com.neosoft.architecture.domain.usecases

import com.neosoft.architecture.data.net.RestApi
import com.neosoft.architecture.presentation.ui.model.SignInModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vijay on 7/3/19.
 */

class NetworkingUC {

    var mRestApi: RestApi? = null

    @Inject
    constructor(mRestApi: RestApi?) {
        this.mRestApi = mRestApi
    }

    fun doSignInUC(username: String, password: String): Observable<SignInModel>? {
        return mRestApi?.doSignInApi(username, password)
    }


}