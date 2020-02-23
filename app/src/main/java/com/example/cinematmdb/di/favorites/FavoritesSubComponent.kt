package com.example.cinematmdb.di.favorites

import com.example.cinematmdb.favorites.FavoriteMoviesFragment
import dagger.Subcomponent

@FavoritesScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoritesSubComponent {
    fun inject(favoriteMoviesFragment: FavoriteMoviesFragment)
}