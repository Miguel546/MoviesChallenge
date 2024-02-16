package com.luismiguel.movieschallenge.data.network.response

import com.luismiguel.movieschallenge.domain.model.DatesModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatesResponse(
    @field:Json(name = "maximum") val maximum: String,
    @field:Json(name = "minimum") val minimum: String
)

fun DatesResponse.toDomain() = DatesModel(
    maximum,
    minimum
)