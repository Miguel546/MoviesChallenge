package com.luismiguel.movieschallenge.data.network.response

import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResultResponse(
    @field:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "backdrop_path") val backdrop_path: String ?= null,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_language") val original_language: String ?= null,
    @field:Json(name = "original_title") val original_title: String ?= null,
    @field:Json(name = "overview") val overview: String ?= "",
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val poster_path: String ?= null,
    @field:Json(name = "release_date") val release_date: String ?= null,
    @field:Json(name = "title") val title: String ?= "",
    @field:Json(name = "video")  val video: Boolean,
    @field:Json(name = "vote_average")  val vote_average: Double,
    @field:Json(name = "vote_count") val vote_count: Int
)

fun MovieResultResponse.toDomain() = MovieModel(
    adult,
    backdrop_path,
    id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    video,
    vote_average,
    vote_count
)