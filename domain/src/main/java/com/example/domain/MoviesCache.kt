package com.example.domain

import entities.MovieEntity
import entities.Optional
import io.reactivex.Observable

interface MoviesCache {
    fun save(movieEntity: MovieEntity)
    fun remove(movieEntity: MovieEntity)
    fun saveAll(movieEntities: List<MovieEntity>)
    fun getAll(): Observable<List<MovieEntity>>
    fun get(movieId: Int): Observable<Optional<MovieEntity>>
    fun search(query: String): Observable<List<MovieEntity>>
    fun isEmpty(): Observable<Boolean>
    fun clear()
}