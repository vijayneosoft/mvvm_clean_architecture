package com.neosoft.architecture.presentation.di.module

import androidx.lifecycle.ViewModelProvider
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.neosoft.architecture.data.net.RestApi
import com.neosoft.architecture.domain.ctx.emailAuth.EmailAuthProvider
import com.neosoft.architecture.domain.ctx.emailAuth.EmailAuthProviderImpl
import com.neosoft.architecture.domain.usecases.LoginUC
import com.neosoft.architecture.domain.usecases.NetworkingUC
import com.neosoft.architecture.presentation.ui.viewModelFactory.ViewModelFactory
import com.neosoft.architecture.presentation.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Vijay on 10/4/19.
 */

@Module
class DataModule {



    @Provides
    @Singleton
    fun provideViewModelFactory(networkingUC: NetworkingUC, mLoginUC: LoginUC): ViewModelProvider.Factory {
        return ViewModelFactory(networkingUC, mLoginUC)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @Provides
    @Singleton
    fun getRequestHeader(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .build()
            chain.proceed(request)
        }
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun getApiCallInterface(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    /**
     * TODO
     *  networkingUC
     * @param restApi
     */

    @Provides
    @Singleton
    internal fun provideNetworkingUC(restApi: RestApi): NetworkingUC {
        return NetworkingUC(restApi)
    }

    @Provides
    @Singleton
    fun emailAuthProvider(): EmailAuthProvider{
        return EmailAuthProviderImpl(FirebaseAuth.getInstance())
    }


}