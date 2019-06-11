package com.android.weathertestapp.di.component

import com.android.weathertestapp.di.module.FragmentModule
import com.android.weathertestapp.di.scopes.FragmentScope
import com.android.weathertestapp.ui.base.BaseMvpFragment
import dagger.Component


@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(baseMvpFragment: BaseMvpFragment)
}