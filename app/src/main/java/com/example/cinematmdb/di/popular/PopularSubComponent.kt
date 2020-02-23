package com.example.cinematmdb.di.popular

import com.example.cinematmdb.popular.PopularMoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [PopularMoviesModule::class])
interface PopularSubComponent {
    fun inject(popularMoviesFragment: PopularMoviesFragment)
}