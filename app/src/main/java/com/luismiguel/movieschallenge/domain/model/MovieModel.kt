package com.luismiguel.movieschallenge.domain.model

import android.os.Parcelable
import com.luismiguel.movieschallenge.data.entities.MovieEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel (
    val adult: Boolean,
    val backdroppath: String ?= null,
    val id: Int,
    val originallanguage: String ?= null,
    val originaltitle: String ? = null,
    val overview: String ?= null,
    val popularity: Double,
    val posterpath: String ?= null,
    val releasedate: String ?= null,
    val title: String ?= null,
    val video: Boolean,
    val voteaverage: Double,
    val votecount: Int
) : Parcelable

fun MovieEntity.toDomain() = MovieModel(adult, backdroppath, id, originallanguage, originaltitle, overview, popularity, posterpath, releasedate, title, video, voteaverage, votecount)