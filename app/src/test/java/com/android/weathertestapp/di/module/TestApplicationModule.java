package com.android.weathertestapp.di.module;

import android.app.Application;
import android.content.Context;
import com.android.weathertestapp.data.prefs.AppPreferencesHelper;
import com.android.weathertestapp.data.prefs.IPreferencesHelper;
import com.android.weathertestapp.di.qualifiers.ApplicationContext;
import com.android.weathertestapp.di.scopes.ApplicationScope;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;


@Module
public abstract class TestApplicationModule {

    @Binds
    @ApplicationContext
    public abstract Context provideContext(Application application);

    @Provides
    @ApplicationScope
    static IPreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return spy(appPreferencesHelper);
    }
}
