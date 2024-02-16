package com.luismiguel.movieschallenge.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.luismiguel.movieschallenge.data.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface MovieFlowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data: MovieEntity)

    @Query("SELECT * from MOVIETABLE WHERE ID = :id")
    fun getMovie(id: Int): Flow<List<MovieEntity>>

    @Query("SELECT * from MOVIETABLE ORDER BY TITLE ASC")
    fun getAllMoviesDB(): Flow<List<MovieEntity>>
}