package com.neosoft.architecture.presentation.ui.model

import com.google.gson.annotations.SerializedName
import com.neosoft.architecture.data.enums.Status


/**
 * Created by Vijay on 26/2/19.
 */

class SignInModel {

    @SerializedName("user_msg")
    var user_msg: String? = null

    @SerializedName("data")
    var data: Data? = null

    var error: Throwable? = null

    var status: Status? = null

    var loginModel: SignInModel? = null

    constructor(status: Status, signInModel: SignInModel?, error: Throwable?) {
        this.status = status
        this.loginModel = signInModel
        this.error = error
    }

    companion object {

        fun success(response: SignInModel): SignInModel {
            return SignInModel(Status.SUCCESS, response, null)
        }

        fun error(error: Throwable): SignInModel {
            return SignInModel(Status.ERROR, null, error)
        }
    }

    //object
    inner class Data {

        @SerializedName("id")
        var id: String? = null

        @SerializedName("role_id")
        var role_id: String? = null

        @SerializedName("first_name")
        var first_name: String? = null

        @SerializedName("last_name")
        var last_name: String? = null

        @SerializedName("email")
        var email: String? = null

        @SerializedName("username")
        var username: String? = null

        @SerializedName("profile_pic")
        var profile_pic: String? = null

        @SerializedName("country_id")
        var country_id: String? = null

        @SerializedName("gender")
        var gender: String? = null

        @SerializedName("phone_no")
        var phone_no: String? = null

        @SerializedName("dob")
        var dob: String? = null

        @SerializedName("is_active")
        var is_active: String? = null

        @SerializedName("created")
        var created: String? = null

        @SerializedName("modified")
        var modified: String? = null

        @SerializedName("access_token")
        var access_token: String? = null

    }

}