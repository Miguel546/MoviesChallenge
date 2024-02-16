package com.luismiguel.movieschallenge.data.network.response

import com.luismiguel.movieschallenge.domain.model.UpcomingModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpcomingResponse(
    @field:Json(name = "dates") val dates: DatesResponse,
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<MovieResultResponse>,
    @field:Json(name = "total_pages") val total_pages: Int,
    @field:Json(name = "total_results") val total_results: Int
)

fun UpcomingResponse.toDomain() = UpcomingModel(
    dates = dates.toDomain(),
    page = page,
    results = results.map { it.toDomain() },
    totalpages = total_pages,
    totalresults = total_results
)