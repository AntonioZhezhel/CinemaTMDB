package com.example.domain

import entities.MovieEntity
import entities.Optional
import io.reactivex.Observable

interface MoviesDataStore {
    fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>>
    fun getMovies(): Observable<List<MovieEntity>>
    fun search(query: String): Observable<List<MovieEntity>>
}