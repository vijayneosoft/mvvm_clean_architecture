package com.neosoft.architecture.domain.ctx.emailAuth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations


/**
 * Created by Vijay on 12/3/19.
 */


class EmailAuthProviderImplTest {

    @Mock
    lateinit var mFirebaseAuth: FirebaseAuth
    @Mock
    lateinit var mEmailAuthListener: EmailAuthListener
    @Mock
    lateinit var mAuthTask: Task<AuthResult>
    @Mock
    lateinit var mUser: FirebaseUser

    lateinit var mEmailAuthProviderImpl: EmailAuthProviderImpl

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
        mEmailAuthProviderImpl = EmailAuthProviderImpl(mFirebaseAuth)
    }

    @Test
    fun createUserTest() {
        var email = "test@gmail.com"
        var password = "test@123"
        mEmailAuthProviderImpl!!.createUser(email, password, mEmailAuthListener)

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)

    }

    @Test
    fun onCompleteTest() {
        `when`(mAuthTask.isSuccessful).thenReturn(true)

        mEmailAuthProviderImpl.onComplete(mAuthTask)

        verifyNoMoreInteractions(mEmailAuthListener)

    }


}