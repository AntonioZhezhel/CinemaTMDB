package com.example.data.api.mappers

import com.example.data.api.entities.MovieData
import common.Mapper
import entities.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieEntityDataMapper @Inject constructor() : Mapper<MovieEntity, MovieData>() {

    override fun mapFrom(from: MovieEntity): MovieData {
        return MovieData(
            id = from.id,
            voteCount = from.voteCount,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            adult = from.adult,
            title = from.title,
            posterPath = from.posterPath,
            originalLanguage = from.originalLanguage,
            backdropPath = from.backdropPath,
            originalTitle = from.originalTitle,
            releaseDate = from.releaseDate,
            overview = from.overview
        )
    }
}