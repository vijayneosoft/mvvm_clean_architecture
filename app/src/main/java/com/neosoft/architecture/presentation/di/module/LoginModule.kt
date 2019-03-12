package com.neosoft.architecture.presentation.di.module

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
    fun getEmailAuthProvider(): EmailAuthProvider {
        return EmailAuthProviderImpl()
    }

    @Provides
    @Singleton
    fun getLoginUC(emailAuthProvider: EmailAuthProvider): LoginUC {
        return LoginUC(emailAuthProvider)
    }


}