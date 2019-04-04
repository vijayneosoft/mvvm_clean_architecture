package com.neosoft.architecture.presentation.di.module

import androidx.lifecycle.ViewModelProvider
import com.neosoft.architecture.data.net.RestApi
import com.neosoft.architecture.domain.usecases.LoginUC
import com.neosoft.architecture.domain.usecases.NetworkingUC
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vijay on 12/3/19.
 */

@Module
class NetworkingModule {

    @Provides
    @Singleton
    fun getNetworkingUC(restApi: RestApi): NetworkingUC {
        return NetworkingUC(restApi)
    }

    @Provides
    @Singleton
    fun getViewModelFactory(networkingUC: NetworkingUC, mLoginUC: LoginUC): ViewModelProvider.Factory {
        return ViewModelFactory(networkingUC, mLoginUC)
    }

}