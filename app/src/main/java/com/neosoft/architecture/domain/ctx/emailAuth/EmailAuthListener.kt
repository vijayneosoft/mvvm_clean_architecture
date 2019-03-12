package com.neosoft.architecture.domain.ctx.emailAuth

import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

/**
 * Created by Vijay on 8/3/19.
 */

interface EmailAuthListener {
    fun onSuccess(user : FirebaseUser)
    fun onFailure(exception: Exception?)

}