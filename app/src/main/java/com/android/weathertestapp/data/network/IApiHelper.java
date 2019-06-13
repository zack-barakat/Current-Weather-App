package com.android.weathertestapp.data.network;

import com.android.weathertestapp.data.network.model.WeatherResponseModel;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zack_barakat
 */

public interface IApiHelper {

    @GET("current.json")
    Observable<WeatherResponseModel> getCurrentWeatherForCity(@Query("key") String key, @Query("q") String city);

    class Factory {
        public static final int NETWORK_CALL_TIMEOUT = 30;

        public static IApiHelper create(Retrofit retrofit) {

            return retrofit.create(IApiHelper.class);
        }
    }
}
