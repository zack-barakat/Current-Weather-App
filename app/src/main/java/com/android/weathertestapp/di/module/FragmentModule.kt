package com.android.weathertestapp.di.module

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun provideActivity(): Activity? {
        return fragment.activity
    }

    @Provides
    fun provideFragment(): Fragment {
        return fragment
    }
}
