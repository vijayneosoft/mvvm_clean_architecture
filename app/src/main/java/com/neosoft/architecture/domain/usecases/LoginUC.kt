package com.neosoft.architecture.domain.usecases

import com.neosoft.architecture.data.netCall.RestApi
import com.neosoft.architecture.data.netCall.RestServices
import com.neosoft.architecture.presentation.loginActivity.LoginModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Vijay on 26/2/19.
 */

class LoginUC @Inject constructor(){

    val restApi = RestServices.getClient().create(RestApi::class.java)

    fun doLoginUC(username: String, password: String): Observable<LoginModel> {
        return restApi.doLoginApi(username, password)
    }

}