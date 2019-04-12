package com.neosoft.architecture.domain.usecases

import com.neosoft.architecture.data.net.RestApi
import com.neosoft.architecture.presentation.ui.model.SignInModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vijay on 7/3/19.
 */

class NetworkingUC @Inject constructor(private val restApi: RestApi) {

    fun doSignInUC(username: String, password: String): Observable<SignInModel>? {
        return restApi.doSignInApi(username, password)
    }


}