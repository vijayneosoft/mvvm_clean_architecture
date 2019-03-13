package com.neosoft.architecture.presentation.di.module

import com.google.firebase.auth.FirebaseAuth
import com.neosoft.architecture.domain.ctx.emailAuth.EmailAuthProvider
import com.neosoft.architecture.domain.ctx.emailAuth.EmailAuthProviderImpl
import com.neosoft.architecture.domain.usecases.LoginUC
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vijay on 12/3/19.
 */

@Module
class LoginModule {


    @Provides
    @Singleton
    fun provideEmailAuthProvider(firebaseAuth: FirebaseAuth): EmailAuthProvider {
        return EmailAuthProviderImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideLoginUC(emailAuthProvider: EmailAuthProvider): LoginUC {
        return LoginUC(emailAuthProvider)
    }

    @Provides
    @Singleton
    fun provideFirebaseInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


}