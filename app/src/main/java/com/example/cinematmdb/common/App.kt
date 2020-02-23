package com.example.cinematmdb.common

import android.app.Application
import com.example.cinematmdb.R
import com.example.cinematmdb.di.MainComponent
import com.example.cinematmdb.di.modules.AppModule
import com.example.cinematmdb.di.modules.DataModule
import com.example.cinematmdb.di.modules.NetworkModule
import com.example.cinematmdb.di.popular.PopularMoviesModule
import com.example.cinematmdb.di.popular.PopularSubComponent
import com.squareup.leakcanary.LeakCanary

class App: Application() {

    lateinit var mainComponent: MainComponent
    private var popularMoviesComponent: PopularSubComponent? = null


    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

      //  initDependencies()
    }

//    private fun initDependencies() {
//        mainComponent = DaggerMainComponent.builder()
//            .appModule(AppModule(applicationContext))
//            .networkModule(NetworkModule(getString(R.string.api_base_url), getString(R.string.api_key)))
//            .dataModule(DataModule())
//            .build()
//
//    }

    fun createPopularComponenet(): PopularSubComponent {
        popularMoviesComponent = mainComponent.plus(PopularMoviesModule())
        return popularMoviesComponent!!
    }
    fun releasePopularComponent() {
        popularMoviesComponent = null
    }


}