package com.android.weathertestapp.ui.currentweather

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.android.weathertestapp.R
import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import com.android.weathertestapp.ui.base.BaseMvpActivity
import com.android.weathertestapp.ui.base.BasePresenter
import kotlinx.android.synthetic.main.activity_current_weather.*
import javax.inject.Inject

class CurrentWeatherActivity : BaseMvpActivity(), CurrentWeatherContracts.View {

    @Inject
    lateinit var mPresenter: CurrentWeatherContracts.Presenter<CurrentWeatherContracts.View>

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_weather)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    private fun setupLayout() {
        spinnerCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>) {}
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                mPresenter.onCityChanged(parent.getItemAtPosition(position) as String)
            }
        }
    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showSelectedCity(city: String) {
        tvCity.text = city
    }

    override fun updateCurrentWeatherUi(currentWeatherEntity: CurrentWeatherEntity) {
        with(currentWeatherEntity) {
            tvWeatherTime.text = updatedTime
            tvConditionDescription.text = weather
            tvTemperature.text = temperature
            tvFeelsLikeTemperature.text = String.format(getString(R.string.text_feels_like), feelsLikeTemperature)
            tvWind.text = String.format(getString(R.string.text_wind), wind)
            tvPrecipitation.text = String.format(getString(R.string.text_precipitation), precipitation)
            tvVisibility.text = String.format(getString(R.string.text_visibility), visibility)
        }
    }

    override fun showImageCondition(imageResource: Int) {
        ivConditionIcon.setImageResource(imageResource)
    }
}
