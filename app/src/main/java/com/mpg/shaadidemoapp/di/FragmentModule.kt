package com.mpg.shaadidemoapp.di

import com.mpg.shaadidemoapp.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    /*
    * We define the name of the Fragment we are going
    * to inject the ViewModelFactory into. i.e. in our case
    * The name of the fragment: HomeFragment
    */
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}