package com.neosoft.architecture.presentation.di.module

import android.app.Activity
import com.neosoft.architecture.presentation.di.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vijay on 11/4/19.
 */

@Module
class ActivityModule(val mActivity: Activity) {

    @Provides
    @PerActivity
    fun provideActivityModule() : Activity {
        return mActivity
    }

}