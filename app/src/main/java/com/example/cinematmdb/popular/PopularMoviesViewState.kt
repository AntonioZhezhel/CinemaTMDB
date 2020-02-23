package com.example.cinematmdb.popular

import com.example.cinematmdb.entities.Movie

data class PopularMoviesViewState(
    var showLoading: Boolean = true,
    var movies: List<Movie>? = null
)