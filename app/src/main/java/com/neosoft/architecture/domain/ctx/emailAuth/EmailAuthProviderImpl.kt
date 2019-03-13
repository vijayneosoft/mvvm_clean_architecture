package com.neosoft.architecture.domain.ctx.emailAuth

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Vijay on 7/3/19.
 */

class EmailAuthProviderImpl : EmailAuthProvider, OnCompleteListener<AuthResult> {

    var mFirebaseAuth: FirebaseAuth? = null
    var mEmailAuthListener: EmailAuthListener? = null

    constructor(firebaseAuth: FirebaseAuth) {
        mFirebaseAuth = firebaseAuth
    }

    override fun createUser(email: String, password: String, emailAuthListener: EmailAuthListener) {
        this.mEmailAuthListener = emailAuthListener
        mFirebaseAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this)

    }

    override fun onComplete(auth: Task<AuthResult>) {
        if (auth.isSuccessful) {
            Log.d("FIREBASE_TAG", "" + auth.result!!.user)
            mEmailAuthListener!!.onSuccess(auth.result!!.user)

        } else {
            Log.d("FIREBASE_TAG", "" + auth.exception)
            mEmailAuthListener!!.onFailure(auth.exception)

        }

    }

}