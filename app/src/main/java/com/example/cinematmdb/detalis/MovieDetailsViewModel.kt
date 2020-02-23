package com.example.cinematmdb.detalis

import androidx.lifecycle.MutableLiveData
import com.example.cinematmdb.common.BaseViewModel
import com.example.cinematmdb.common.SingleLiveEvent
import com.example.cinematmdb.entities.Movie
import common.Mapper
import entities.MovieEntity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import usecases.CheckFavoriteStatus
import usecases.GetMovieDetails
import usecases.RemoveFavoriteMovie
import usecases.SaveFavoriteMovie

class MovieDetailsViewModel(private val getMovieDetails: GetMovieDetails,
                            private val saveFavoriteMovie: SaveFavoriteMovie,
                            private val removeFavoriteMovie: RemoveFavoriteMovie,
                            private val checkFavoriteStatus: CheckFavoriteStatus,
                            private val mapper: Mapper<MovieEntity, Movie>,
                            private val movieId: Int) : BaseViewModel() {

    lateinit var movieEntity: MovieEntity
    var viewState: MutableLiveData<MovieDetailsViewState> = MutableLiveData()
    var favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable> = SingleLiveEvent()

    init {
        viewState.value = MovieDetailsViewState(isLoading = true)
    }

    fun getMovieDetails() {
        addDisposable(
            getMovieDetails.getById(movieId)
                .map {
                    it.value?.let {
                        movieEntity = it
                        mapper.mapFrom(movieEntity)
                    } ?: run {
                        throw Throwable("Something went wrong :(")
                    }
                }
                .zipWith(checkFavoriteStatus.check(movieId), BiFunction<Movie, Boolean, Movie> { movie, isFavorite ->
                    movie.isFavorite = isFavorite
                    return@BiFunction movie
                })
                .subscribe(
                    { onMovieDetailsReceived(it) },
                    { errorState.value = it }
                )
        )
    }

    fun favoriteButtonClicked() {
        addDisposable(checkFavoriteStatus.check(movieId).flatMap {
            when (it) {
                true -> {
                    removeFavorite(movieEntity)
                }
                false -> {
                    saveFavorite(movieEntity)
                }
            }
        }.subscribe({ isFavorite ->
            favoriteState.value = isFavorite
        }, {
            errorState.value = it
        }))
    }

    private fun onMovieDetailsReceived(movie: Movie) {

        val newViewState = viewState.value?.copy(
            isLoading = false,
            title = movie.originalTitle,
            homepage = movie.details?.homepage,
            overview = movie.details?.overview,
            releaseDate = movie.releaseDate,
            votesAverage = movie.voteAverage,
            backdropUrl = movie.backdropPath,
            genres = movie.details?.genres)

        viewState.value = newViewState
        favoriteState.value = movie.isFavorite
    }

    private fun saveFavorite(movieEntity: MovieEntity): Observable<Boolean> {
        return saveFavoriteMovie.save(movieEntity)
    }

    private fun removeFavorite(movieEntity: MovieEntity): Observable<Boolean> {
        return removeFavoriteMovie.remove(movieEntity)
    }
}