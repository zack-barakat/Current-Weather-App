package com.android.weathertestapp.di.module;

import com.android.weathertestapp.data.TestApiHelper;
import com.android.weathertestapp.data.TestLocalDataSource;
import com.android.weathertestapp.data.TestNetworkDataSource;
import com.android.weathertestapp.data.db.ILocalDataSource;
import com.android.weathertestapp.data.network.IApiHelper;
import com.android.weathertestapp.data.network.INetworkDataSource;
import com.android.weathertestapp.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.spy;

@Module(includes = {TestApplicationModule.class})
public class MockApiModule {

    @Provides
    @ApplicationScope
    IApiHelper provideApiHelper() {
        return spy(new TestApiHelper());
    }

    @Provides
    @ApplicationScope
    ILocalDataSource provideLocalDataSource() {
        return spy(new TestLocalDataSource());
    }

    @Provides
    @ApplicationScope
    INetworkDataSource provideNetworkDataSource() {
        return spy(new TestNetworkDataSource());
    }
}
