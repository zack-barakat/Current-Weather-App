package com.android.weathertestapp;

import android.content.Context;
import androidx.annotation.NonNull;
import com.android.weathertestapp.data.IDataManager;
import com.android.weathertestapp.data.db.ILocalDataSource;
import com.android.weathertestapp.data.network.IApiHelper;
import com.android.weathertestapp.data.network.INetworkDataSource;
import com.android.weathertestapp.data.repositories.IWeatherRepository;
import com.android.weathertestapp.di.component.TestApplicationComponent;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import javax.inject.Inject;


@RunWith(RobolectricTestRunner.class)
@Config(
        application = MockApplication.class,
        sdk = 27,
        manifest = Config.NONE
)
public abstract class AppRobolectricTestCase extends TestCase {
    private MockApplication application;

    @Inject
    public IDataManager appDataManager;

    @Inject
    public IWeatherRepository weatherRepository;

    @Inject
    public ILocalDataSource localDataSource;

    @Inject
    public INetworkDataSource networkDataSource;

    @Inject
    public IApiHelper apiHelper;

    @Override
    @Before
    public void setUp() throws Exception {
        component().inject(this);
        MockitoAnnotations.initMocks(this);
        super.setUp();
    }

    @NonNull
    protected MockApplication application() {
        if (application != null) {
            return application;
        }

        application = (MockApplication) RuntimeEnvironment.application;
        return application;
    }


    @NonNull
    protected TestApplicationComponent component() {
        if (application != null) {
            return (TestApplicationComponent) application.getComponent();
        }

        application = (MockApplication) RuntimeEnvironment.application;
        return (TestApplicationComponent) application.getComponent();
    }


    @NonNull
    protected Context context() {
        return application().getApplicationContext();
    }
}
