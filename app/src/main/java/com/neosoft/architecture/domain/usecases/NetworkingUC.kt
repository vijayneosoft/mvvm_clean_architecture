package com.neosoft.architecture.domain.usecases

import com.neosoft.architecture.data.netCall.RestApi
import com.neosoft.architecture.presentation.ui.model.SignInModel
import io.reactivex.Observable

/**
 * Created by Vijay on 7/3/19.
 */
class NetworkingUC {

    var mRestApi: RestApi? = null

    constructor(mRestApi: RestApi?) {
        this.mRestApi = mRestApi
    }

    fun doSignInUC(username: String, password: String): Observable<SignInModel>? {
        return mRestApi?.doSignInApi(username, password)
    }


}