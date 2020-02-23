package com.example.data.api.mappers

import com.example.data.api.entities.DetailsData
import common.Mapper
import entities.GenreEntity
import entities.MovieDetailsEntity
import entities.MovieEntity
import entities.ReviewEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsDataMovieEntityMapper @Inject constructor() : Mapper<DetailsData, MovieEntity>() {

    override fun mapFrom(from: DetailsData): MovieEntity {
        val movieEntity = MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            video = from.video,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            adult = from.adult,
            title = from.title,
            posterPath = from.posterPath,
            originalTitle = from.originalTitle,
            backdropPath = from.backdropPath,
            originalLanguage = from.originalLanguage,
            releaseDate = from.releaseDate,
            overview = from.overview
        )
        val details = MovieDetailsEntity()
        details.overview = from.overview
        details.budget = from.budget
        details.homepage = from.homepage
        details.imdbId = from.imdbId
        details.revenue = from.revenue
        details.runtime = from.runtime
        details.tagline = from.tagline

        from.genres?.let {
            val genreEntities = it.map { genreData ->
                return@map GenreEntity(genreData.id, genreData.name)
            }
            details.genres = genreEntities
        }

        from.reviews?.let {
            val reviewEntities = it.results?.map { reviewData ->
                return@map ReviewEntity(
                    id = reviewData.id,
                    author = reviewData.author,
                    content = reviewData.content
                )
            }

            details.reviews = reviewEntities
        }
        movieEntity.details = details
        return movieEntity
    }

}