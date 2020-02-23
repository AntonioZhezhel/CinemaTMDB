package com.example.data.api.repositories

import com.example.domain.MoviesCache
import com.example.domain.MoviesDataStore
import entities.MovieEntity
import entities.Optional
import io.reactivex.Observable

class CachedMoviesDataStore(private val moviesCache: MoviesCache): MoviesDataStore {

    override fun search(query: String): Observable<List<MovieEntity>> {
        return moviesCache.search(query)
    }

    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return moviesCache.get(movieId)
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }

    fun isEmpty(): Observable<Boolean> {
        return moviesCache.isEmpty()
    }

    fun saveAll(movieEntities: List<MovieEntity>) {
        moviesCache.saveAll(movieEntities)
    }
}