package com.example.cinematmdb.di

import com.example.cinematmdb.di.modules.AppModule
import com.example.cinematmdb.di.modules.DataModule
import com.example.cinematmdb.di.modules.NetworkModule
import com.example.cinematmdb.di.popular.PopularMoviesModule
import com.example.cinematmdb.di.popular.PopularSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetworkModule::class),
    (DataModule::class)
])

interface MainComponent {
    fun plus(popularMoviesModule: PopularMoviesModule): PopularSubComponent

}