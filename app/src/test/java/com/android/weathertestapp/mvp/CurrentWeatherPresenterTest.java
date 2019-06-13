package com.android.weathertestapp.mvp;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.android.weathertestapp.AppRobolectricTestCase;
import com.android.weathertestapp.data.TestDataGenerator;
import com.android.weathertestapp.rule.TestSchedulersRule;
import com.android.weathertestapp.ui.currentweather.CurrentWeatherContracts;
import com.android.weathertestapp.ui.currentweather.CurrentWeatherPresenter;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

public class CurrentWeatherPresenterTest extends AppRobolectricTestCase {

    @Rule
    public TestSchedulersRule rule = new TestSchedulersRule();

    @Inject
    CurrentWeatherPresenter presenter;

    @Mock
    CurrentWeatherContracts.View view;

    @Before
    public void setUp() throws Exception {
        component().inject(this);
        super.setUp();
        presenter = spy(presenter);
    }

    @Test
    public void onSelectCity_offline_shouldShowWeatherFromLocal() {
        //Given
        presenter.onAttachView(view);
        WifiManager wifi = (WifiManager) context().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
        when(localDataSource.getCurrentWeatherForCity(anyString())).thenReturn(Flowable.just(TestDataGenerator.INSTANCE.getWeatherEntity()));
        //When
        presenter.onCityChanged(anyString());
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).updateCurrentWeatherUi(TestDataGenerator.INSTANCE.getWeatherEntity());
        verify(view).showSelectedCity(anyString());
        verify(view).showImageCondition(anyInt());
    }

    @Test
    public void onSelectCity_onlineMode_shouldShowWeatherFromApi() {
        //Given
        presenter.onAttachView(view);
        when(apiHelper.getCurrentWeatherForCity(anyString(), anyString())).thenReturn(Observable.just(TestDataGenerator.INSTANCE.getWeatherResponseModel()));
        //When
        presenter.onCityChanged(anyString());
        rule.advanceTimeBy(1, TimeUnit.SECONDS);
        //verify
        verify(view).updateCurrentWeatherUi(TestDataGenerator.INSTANCE.getWeatherEntity());
        verify(view).showSelectedCity(anyString());
        verify(view).showImageCondition(anyInt());
    }

    @After
    public void unsubAll() {
        presenter.onDestroyView();
    }
}