package com.example.cinematmdb.di.details

import com.example.cinematmdb.MovieEntityMovieMapper
import com.example.cinematmdb.common.ASyncTransformer
import com.example.cinematmdb.detalis.MovieDetailsVMFactory
import com.example.cinematmdb.di.DI
import com.example.domain.MoviesCache
import com.example.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import usecases.CheckFavoriteStatus
import usecases.GetMovieDetails
import usecases.RemoveFavoriteMovie
import usecases.SaveFavoriteMovie
import javax.inject.Named

@Module
class MovieDetailsModule {

    @Provides
    fun provideRemoveFavoriteMovie(@Named(DI.favoritesCache) moviesCache: MoviesCache): RemoveFavoriteMovie {
        return RemoveFavoriteMovie(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideCheckFavoriteStatus(@Named(DI.favoritesCache) moviesCache: MoviesCache): CheckFavoriteStatus {
        return CheckFavoriteStatus(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideSaveFavoriteMovieUseCase(@Named(DI.favoritesCache) moviesCache: MoviesCache): SaveFavoriteMovie {
        return SaveFavoriteMovie(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepository: MoviesRepository): GetMovieDetails {
        return GetMovieDetails(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideMovieDetailsVMFactory(getMovieDetails: GetMovieDetails,
                                     saveFavoriteMovie: SaveFavoriteMovie,
                                     removeFavoriteMovie: RemoveFavoriteMovie,
                                     checkFavoriteStatus: CheckFavoriteStatus,
                                     mapper: MovieEntityMovieMapper
    ): MovieDetailsVMFactory {
        return MovieDetailsVMFactory(getMovieDetails, saveFavoriteMovie, removeFavoriteMovie, checkFavoriteStatus, mapper)
    }
}