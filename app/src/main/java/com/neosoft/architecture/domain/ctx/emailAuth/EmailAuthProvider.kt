package com.neosoft.architecture.domain.ctx.emailAuth

/**
 * Created by Vijay on 8/3/19.
 */

interface EmailAuthProvider {

    fun createUser(email: String, password: String, emailAuthListener: EmailAuthListener)

}