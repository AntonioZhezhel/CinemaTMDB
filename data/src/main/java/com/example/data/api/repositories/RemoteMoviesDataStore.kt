package com.example.data.api.repositories

import com.example.data.api.api.Api
import com.example.data.api.mappers.DetailsDataMovieEntityMapper
import com.example.data.api.mappers.MovieDataEntityMapper
import com.example.domain.MoviesDataStore
import entities.MovieEntity
import entities.Optional
import io.reactivex.Observable

class RemoteMoviesDataStore(private val api: Api) : MoviesDataStore {

    private val movieDataMapper = MovieDataEntityMapper()
    private val detailedDataMapper = DetailsDataMovieEntityMapper()

    override fun search(query: String): Observable<List<MovieEntity>> {
        return api.searchMovies(query).map { results ->
            results.movies.map { movieDataMapper.mapFrom(it) }
        }
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return api.getPopularMovies().map { results ->
            results.movies.map { movieDataMapper.mapFrom(it) }
        }
    }

    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return api.getMovieDetails(movieId).flatMap { detailedData ->
            Observable.just(Optional.of(detailedDataMapper.mapFrom(detailedData)))
        }
    }
}
