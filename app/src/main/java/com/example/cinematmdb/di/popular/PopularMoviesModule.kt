package com.example.cinematmdb.di.popular

import com.example.cinematmdb.MovieEntityMovieMapper
import com.example.cinematmdb.common.ASyncTransformer
import com.example.cinematmdb.popular.PopularMoviesVMFactory
import com.example.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import usecases.GetPopularMovies

@PopularScope
@Module
class PopularMoviesModule {
    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMovies {
        return GetPopularMovies(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun providePopularMoviesVMFactory(useCase: GetPopularMovies, mapper: MovieEntityMovieMapper): PopularMoviesVMFactory {
        return PopularMoviesVMFactory(useCase, mapper)
    }
}