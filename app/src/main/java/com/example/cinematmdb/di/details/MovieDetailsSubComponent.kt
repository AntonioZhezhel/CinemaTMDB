package com.example.cinematmdb.di.details

import com.example.cinematmdb.detalis.MovieDetailsActivity
import dagger.Subcomponent
import javax.inject.Scope

@DetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}