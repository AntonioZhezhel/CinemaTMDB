package com.example.cinematmdb.detalis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinematmdb.entities.Movie
import common.Mapper
import entities.MovieEntity
import usecases.CheckFavoriteStatus
import usecases.GetMovieDetails
import usecases.RemoveFavoriteMovie
import usecases.SaveFavoriteMovie

class MovieDetailsVMFactory(
    private val getMovieDetails: GetMovieDetails,
    private val saveFavoriteMovie: SaveFavoriteMovie,
    private val removeFavoriteMovie: RemoveFavoriteMovie,
    private val checkFavoriteStatus: CheckFavoriteStatus,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    var movieId: Int = -1

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(
            getMovieDetails,
            saveFavoriteMovie,
            removeFavoriteMovie,
            checkFavoriteStatus,
            mapper,
            movieId) as T //TODO: solve casting issue
    }

}