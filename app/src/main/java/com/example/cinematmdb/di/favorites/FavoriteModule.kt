package com.example.cinematmdb.di.favorites

import com.example.cinematmdb.MovieEntityMovieMapper
import com.example.cinematmdb.common.ASyncTransformer
import com.example.cinematmdb.di.DI
import com.example.cinematmdb.favorites.FavoriteMoviesVMFactory
import com.example.domain.MoviesCache
import dagger.Module
import dagger.Provides
import usecases.GetFavoriteMovies
import javax.inject.Named

@Module
class FavoriteModule {

    @Provides
    fun provideGetFavoriteMovies(@Named(DI.favoritesCache) moviesCache: MoviesCache): GetFavoriteMovies {
        return GetFavoriteMovies(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideFavoriteMoviesVMFactory(getFavoriteMovies: GetFavoriteMovies,
                                       movieEntityMoveMapper: MovieEntityMovieMapper
    ): FavoriteMoviesVMFactory {
        return FavoriteMoviesVMFactory(getFavoriteMovies, movieEntityMoveMapper)
    }
}