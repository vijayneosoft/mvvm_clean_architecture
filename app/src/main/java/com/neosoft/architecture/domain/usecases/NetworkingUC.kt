package com.neosoft.architecture.domain.usecases

import com.neosoft.architecture.data.netCall.RestApi
import com.neosoft.architecture.presentation.ui.model.LoginModel
import io.reactivex.Observable

/**
 * Created by Vijay on 7/3/19.
 */
class NetworkUC {

    var mRestApi: RestApi? = null

    constructor(mRestApi: RestApi?) {
        this.mRestApi = mRestApi
    }

    fun doLoginUC(username: String, password: String): Observable<LoginModel> {
        return mRestApi!!.doLoginApi(username, password)
    }

}