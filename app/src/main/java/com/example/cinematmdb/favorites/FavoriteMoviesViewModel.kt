package com.example.cinematmdb.favorites

import androidx.lifecycle.MutableLiveData
import com.example.cinematmdb.common.BaseViewModel
import com.example.cinematmdb.common.SingleLiveEvent
import com.example.cinematmdb.entities.Movie
import common.Mapper
import entities.MovieEntity
import usecases.GetFavoriteMovies

class FavoriteMoviesViewModel(private val getFavoriteMovies: GetFavoriteMovies,
                              private val movieEntityMovieMapper: Mapper<MovieEntity, Movie>
) : BaseViewModel() {

    var viewState: MutableLiveData<FavoritesMoviesViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        val viewState = FavoritesMoviesViewState()
        this.viewState.value = viewState
    }

    fun getFavorites() {
        getFavoriteMovies.observable()
            .flatMap { movieEntityMovieMapper.observable(it) }
            .subscribe({ movies ->
                val newViewState = viewState.value?.copy(
                    isEmpty = movies.isEmpty(),
                    isLoading = false,
                    movies = movies)
                viewState.value = newViewState
                errorState.value = null

            }, {
                viewState.value = viewState.value?.copy(isLoading = false, isEmpty = false)
                errorState.value = it

            })
    }

}