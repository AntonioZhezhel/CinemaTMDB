package com.example.data.api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.api.entities.MovieData

@Database(entities = arrayOf(MovieData::class),version = 1)
abstract class MoviesDatabase:RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}