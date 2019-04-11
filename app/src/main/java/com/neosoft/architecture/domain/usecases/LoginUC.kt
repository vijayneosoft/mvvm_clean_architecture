package com.neosoft.architecture.domain.usecases

import com.neosoft.architecture.domain.ctx.emailAuth.EmailAuthListener
import com.neosoft.architecture.domain.ctx.emailAuth.EmailAuthProvider
import javax.inject.Inject

/**
 * Created by Vijay on 8/3/19.
 */

class LoginUC {

    var mEmailAuthProvider: EmailAuthProvider? = null

    @Inject
    constructor(emailAuthProvider: EmailAuthProvider) {
        this.mEmailAuthProvider = emailAuthProvider
    }

    /**
     * TODO
     *
     * @param email
     * @param password
     * @param emailAuthListener
     */

    fun emailVerification(email: String, password: String, emailAuthListener: EmailAuthListener) {
        mEmailAuthProvider?.createUser(email, password, emailAuthListener)

    }


}