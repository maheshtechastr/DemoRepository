package com.mpg.shaadidemoapp

import android.app.Activity
import android.app.Application
import com.mpg.shaadidemoapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    /** Returns an [AndroidInjector] of [Activity]s.  */
    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return androidInjector
    }
}