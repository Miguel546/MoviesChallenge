package com.luismiguel.movieschallenge.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luismiguel.movieschallenge.domain.model.MovieModel
@Entity(tableName = "movietable")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdroppath")
    val backdroppath: String ?= null,
    @ColumnInfo(name = "originallanguage")
    val originallanguage: String ?= null,
    @ColumnInfo(name = "originaltitle")
    val originaltitle: String ? = null,
    @ColumnInfo(name = "overview")
    val overview: String ?= null,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "posterpath")
    val posterpath: String ?= null,
    @ColumnInfo(name = "releasedate")
    val releasedate: String ?= null,
    @ColumnInfo(name = "title")
    val title: String ?= null,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "voteaverage")
    val voteaverage: Double,
    @ColumnInfo(name = "votecount")
    val votecount: Int
)

fun MovieModel.toDatabase() = MovieEntity(id, adult, backdroppath, originallanguage, originaltitle, overview, popularity, posterpath, releasedate, title, video, voteaverage, votecount)