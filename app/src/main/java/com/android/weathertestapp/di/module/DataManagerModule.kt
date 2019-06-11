package com.android.weathertestapp.di.module


import com.android.weathertestapp.data.AppErrorHelper
import com.android.weathertestapp.data.DataManager
import com.android.weathertestapp.data.IAppErrorHelper
import com.android.weathertestapp.data.IDataManager
import com.android.weathertestapp.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by zack_barakat
 */

@Module(includes = [ApiModule::class])
class DataManagerModule {

    @Provides
    @ApplicationScope
    fun provideDataManager(manager: DataManager): IDataManager {
        return manager
    }

    @Provides
    @ApplicationScope
    fun provideErrorHelper(errorHelper: AppErrorHelper): IAppErrorHelper {
        return errorHelper
    }
}
