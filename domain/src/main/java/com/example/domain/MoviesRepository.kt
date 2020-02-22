package com.example.domain

import entities.MovieEntity
import entities.Optional
import io.reactivex.Observable

interface MoviesRepository {
    fun getMovies(): Observable<List<MovieEntity>>
    fun search(query: String): Observable<List<MovieEntity>>
    fun getMovie(movieId: Int): Observable<Optional<MovieEntity>>
}