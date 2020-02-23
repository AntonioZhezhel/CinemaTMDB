package com.example.cinematmdb.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinematmdb.entities.Movie
import common.Mapper
import entities.MovieEntity
import usecases.GetFavoriteMovies

class FavoriteMoviesVMFactory(private val useCase: GetFavoriteMovies,
                              private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteMoviesViewModel(useCase, mapper) as T
    }

}