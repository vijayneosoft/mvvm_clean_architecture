package com.neosoft.architecture.domain.usecases

import com.neosoft.architecture.data.netCall.RestApi
import com.neosoft.architecture.data.netCall.RestServices
import com.neosoft.architecture.presentation.loginActivity.LoginModel
import io.reactivex.Observable

/**
 * Created by Vijay on 26/2/19.
 */

class LoginUC {

    var restApi: RestApi

    constructor() {
        restApi = RestServices.getClient().create(RestApi::class.java)
    }

    fun doLoginUC(username: String, password: String): Observable<LoginModel> {
        return restApi.doLoginApi(username, password)
    }

}