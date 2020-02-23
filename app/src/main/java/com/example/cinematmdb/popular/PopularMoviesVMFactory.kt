package com.example.cinematmdb.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinematmdb.entities.Movie
import common.Mapper
import entities.MovieEntity
import usecases.GetPopularMovies

class PopularMoviesVMFactory(private val useCase: GetPopularMovies,
                             private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(useCase, mapper) as T
    }

}