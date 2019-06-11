package com.android.weathertestapp.di.module

import android.app.Activity
import android.content.Context
import com.android.weathertestapp.di.qualifiers.ActivityContext
import com.android.weathertestapp.ui.splash.SplashContracts
import com.android.weathertestapp.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideSplashPresenter(splashPresenter: SplashPresenter): SplashContracts.Presenter<SplashContracts.View> {
        return splashPresenter
    }
}
