package com.example.data.api.db

import com.example.data.api.entities.MovieData
import com.example.domain.MoviesCache
import common.Mapper
import entities.MovieEntity
import entities.Optional
import io.reactivex.Observable

class RoomFavoritesMovieCache(database: MoviesDatabase,
                              private val entityToDataMapper: Mapper<MovieEntity, MovieData>,
                              private val dataToEntityMapper: Mapper<MovieData, MovieEntity>) :
    MoviesCache {
    private val dao: MoviesDao = database.getMoviesDao()

    override fun clear() {
        dao.clear()
    }

    override fun save(movieEntity: MovieEntity) {
        dao.saveMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override fun remove(movieEntity: MovieEntity) {
        dao.removeMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override fun saveAll(movieEntities: List<MovieEntity>) {
        dao.saveAllMovies(movieEntities.map { entityToDataMapper.mapFrom(it) })
    }

    override fun getAll(): Observable<List<MovieEntity>> {
        return Observable.fromCallable { dao.getFavorites().map { dataToEntityMapper.mapFrom(it) } }
    }

    override fun get(movieId: Int): Observable<Optional<MovieEntity>> {

        return Observable.fromCallable {
            val movieData = dao.get(movieId)
            movieData?.let {
                Optional.of(dataToEntityMapper.mapFrom(it))
            } ?: Optional.empty()
        }
    }

    override fun isEmpty(): Observable<Boolean> {
        return Observable.fromCallable { dao.getFavorites().isEmpty() }
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        val searchQuery = "%$query%"
        return Observable.fromCallable {
            dao.search(searchQuery).map { dataToEntityMapper.mapFrom(it) }
        }
    }
}