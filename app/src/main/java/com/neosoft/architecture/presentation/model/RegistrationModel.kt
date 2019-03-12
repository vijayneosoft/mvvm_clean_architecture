package com.neosoft.architecture.presentation.model

import com.google.firebase.auth.FirebaseUser
import com.neosoft.architecture.data.enums.Status

/**
 * Created by Vijay on 11/3/19.
 */

class RegistrationModel {

    var mUser: FirebaseUser? = null
    var mError: Exception? = null
    var mStatus: Status? = null

    constructor(mStatus: Status?, mUser: FirebaseUser?, mError: Exception?) {
        this.mStatus = mStatus
        this.mUser = mUser
        this.mError = mError
    }

    companion object {
        fun loading(): RegistrationModel {
            return RegistrationModel(Status.LOADING, null, null)
        }

        fun success(user: FirebaseUser): RegistrationModel {
            return RegistrationModel(Status.SUCCESS, user, null)
        }

        fun error(error: Exception?): RegistrationModel {
            return RegistrationModel(Status.ERROR, null, error)
        }

    }


}