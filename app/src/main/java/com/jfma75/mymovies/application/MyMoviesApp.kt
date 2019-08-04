package com.jfma75.mymovies.application

import android.app.Application
import com.jfma75.mymovies.di.DaggerMyMoviesComponent
import com.jfma75.mymovies.di.MyMoviesComponent
import com.jfma75.mymovies.ui.common.ILogger

class MyMoviesApp: Application(), ILogger {
    lateinit var component: MyMoviesComponent
        private set

    companion object {
        var instance: MyMoviesApp by DelegatesExt.NotNullSingleValue()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerMyMoviesComponent.factory().create(this)
    }
}