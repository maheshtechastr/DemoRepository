package com.mpg.shaadidemoapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpg.nagarro.deviceinventorymgmt.di.scope.ViewModelKey
import com.mpg.shaadidemoapp.factory.ViewModelFactory
import com.mpg.shaadidemoapp.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  HomeViewModel.class as key,
     * and a Provider that will build a HomeViewModel
     * object.
     *
     * */
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    protected abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel

}
