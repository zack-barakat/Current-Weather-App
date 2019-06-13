package com.android.weathertestapp.di.component;

import android.app.Application;
import com.android.weathertestapp.AppRobolectricTestCase;
import com.android.weathertestapp.di.module.MockApiModule;
import com.android.weathertestapp.di.module.TestApplicationModule;
import com.android.weathertestapp.di.module.TestDataManagerModule;
import com.android.weathertestapp.di.scopes.ApplicationScope;
import com.android.weathertestapp.mvp.CurrentWeatherPresenterTest;
import dagger.BindsInstance;
import dagger.Component;

@ApplicationScope
@Component(modules = {
        TestDataManagerModule.class,
        MockApiModule.class,
        TestApplicationModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(AppRobolectricTestCase appRobolectricTestCase);

    void inject(CurrentWeatherPresenterTest currentWeatherPresenterTest);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TestApplicationComponent.Builder application(Application application);

        TestApplicationComponent build();
    }
}
