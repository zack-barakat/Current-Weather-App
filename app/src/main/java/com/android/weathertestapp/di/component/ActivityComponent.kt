package com.android.weathertestapp.di.component


import com.android.weathertestapp.di.module.ActivityModule
import com.android.weathertestapp.di.scopes.ActivityScope
import com.android.weathertestapp.ui.base.BaseMvpActivity
import com.android.weathertestapp.ui.splash.SplashActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: BaseMvpActivity)

    fun inject(activity: SplashActivity)

}