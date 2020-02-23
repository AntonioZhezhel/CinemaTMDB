package com.example.cinematmdb.favorites

import com.example.cinematmdb.entities.Movie

data class FavoritesMoviesViewState(
    val isLoading: Boolean = true,
    val isEmpty: Boolean = true,
    val movies: List<Movie>? = null
)