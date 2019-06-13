package com.android.weathertestapp.di.module;

import com.android.weathertestapp.data.AppErrorHelper;
import com.android.weathertestapp.data.DataManager;
import com.android.weathertestapp.data.IAppErrorHelper;
import com.android.weathertestapp.data.IDataManager;
import com.android.weathertestapp.data.repositories.IWeatherRepository;
import com.android.weathertestapp.data.repositories.WeatherRepository;
import com.android.weathertestapp.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;


@Module
public class TestDataManagerModule {

    @Provides
    @ApplicationScope
    IDataManager provideIAppDataManager(DataManager testDataManager) {
        return spy(testDataManager);
    }

    @Provides
    @ApplicationScope
    IWeatherRepository provideIWeatherRepository(WeatherRepository weatherRepository) {
        return spy(weatherRepository);
    }

    @Provides
    @ApplicationScope
    IAppErrorHelper provideIAppErrorHelper(AppErrorHelper errorHelper) {
        return spy(errorHelper);
    }
}
