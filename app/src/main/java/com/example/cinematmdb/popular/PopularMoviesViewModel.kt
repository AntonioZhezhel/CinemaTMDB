package com.example.cinematmdb.popular

import androidx.lifecycle.MutableLiveData
import com.example.cinematmdb.common.BaseViewModel
import com.example.cinematmdb.common.SingleLiveEvent
import com.example.cinematmdb.entities.Movie
import common.Mapper
import entities.MovieEntity
import usecases.GetPopularMovies

class PopularMoviesViewModel(private val getPopularMovies: GetPopularMovies,
                             private val movieEntityMovieMapper: Mapper<MovieEntity, Movie>
) :
    BaseViewModel() {

    var viewState: MutableLiveData<PopularMoviesViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        viewState.value = PopularMoviesViewState()
    }

    fun getPopularMovies() {
        addDisposable(getPopularMovies.observable()
            .flatMap { movieEntityMovieMapper.observable(it) }
            .subscribe({ movies ->
                viewState.value?.let {
                    val newState = this.viewState.value?.copy(showLoading = false, movies = movies)
                    this.viewState.value = newState
                    this.errorState.value = null
                }

            }, {
                viewState.value = viewState.value?.copy(showLoading = false)
                errorState.value = it
            }))
    }
}