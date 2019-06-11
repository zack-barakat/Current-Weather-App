package com.android.weathertestapp.di.module

import android.app.Application
import android.content.Context
import com.android.weathertestapp.data.prefs.AppPreferencesHelper
import com.android.weathertestapp.data.prefs.IPreferencesHelper
import com.android.weathertestapp.di.qualifiers.ApplicationContext
import com.android.weathertestapp.di.scopes.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by zack_barakat
 */

@Module
abstract class ApplicationModule {

    @Binds
    @ApplicationContext
    abstract fun provideContext(application: Application): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ApplicationScope
        internal fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): IPreferencesHelper {
            return appPreferencesHelper
        }
    }
}
