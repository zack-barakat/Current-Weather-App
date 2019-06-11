package com.android.weathertestapp.ui.splash

import android.os.Bundle
import com.android.weathertestapp.R
import com.android.weathertestapp.ui.base.BaseMvpActivity
import com.android.weathertestapp.ui.base.BasePresenter
import javax.inject.Inject

class SplashActivity : BaseMvpActivity(), SplashContracts.View {

    @Inject
    lateinit var mPresenter: SplashContracts.Presenter<SplashContracts.View>

    public override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupLayout()
        mPresenter.onAttachView(this)
    }

    override fun sendExtrasToPresenter(extras: Bundle) {

    }

    protected fun setupLayout() {

    }

    public override fun getPresenter(): BasePresenter<*> {
        return mPresenter
    }

    override fun showMainScreen() {

    }
}
