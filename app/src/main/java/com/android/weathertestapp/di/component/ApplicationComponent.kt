package com.android.weathertestapp.di.component

import android.app.Application
import android.content.Context
import com.android.weathertestapp.App
import com.android.weathertestapp.data.IAppErrorHelper
import com.android.weathertestapp.data.IDataManager
import com.android.weathertestapp.di.module.ApiModule
import com.android.weathertestapp.di.module.ApplicationModule
import com.android.weathertestapp.di.module.DataManagerModule
import com.android.weathertestapp.di.qualifiers.ApplicationContext
import com.android.weathertestapp.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataManagerModule::class, ApiModule::class, ApplicationModule::class])
interface ApplicationComponent {

    val dataManager: IDataManager

    val errorHelper: IAppErrorHelper

    fun inject(app: App)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}