package com.luismiguel.movieschallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luismiguel.movieschallenge.data.dao.MovieFlowDao
import com.luismiguel.movieschallenge.data.entities.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class MovieFlowDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieFlowDao
}