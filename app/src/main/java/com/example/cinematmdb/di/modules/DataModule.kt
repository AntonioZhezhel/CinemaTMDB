package com.example.cinematmdb.di.modules

import android.content.Context
import androidx.room.Room
import com.example.cinematmdb.di.DI
import com.example.data.api.api.Api
import com.example.data.api.db.MoviesDatabase
import com.example.data.api.db.RoomFavoritesMovieCache
import com.example.data.api.mappers.MovieDataEntityMapper
import com.example.data.api.mappers.MovieEntityDataMapper
import com.example.data.api.repositories.CachedMoviesDataStore
import com.example.data.api.repositories.MemoryMoviesCache
import com.example.data.api.repositories.MoviesRepositoryImpl
import com.example.data.api.repositories.RemoteMoviesDataStore
import com.example.domain.MoviesCache
import com.example.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies_db").build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: Api,
                               @Named(DI.inMemoryCache) cache: MoviesCache
    ): MoviesRepository {

        val cachedMoviesDataStore = CachedMoviesDataStore(cache)
        val remoteMoviesDataStore = RemoteMoviesDataStore(api)
        return MoviesRepositoryImpl(cachedMoviesDataStore, remoteMoviesDataStore)
    }

    @Singleton
    @Provides
    @Named(DI.inMemoryCache)
    fun provideInMemoryMoviesCache(): MoviesCache {
        return MemoryMoviesCache()
    }

    @Singleton
    @Provides
    @Named(DI.favoritesCache)
    fun provideFavoriteMoviesCache(moviesDatabase: MoviesDatabase,
                                   entityDataMapper: MovieEntityDataMapper,
                                   dataEntityMapper: MovieDataEntityMapper
    ): MoviesCache {
        return RoomFavoritesMovieCache(moviesDatabase, entityDataMapper, dataEntityMapper)
    }
}