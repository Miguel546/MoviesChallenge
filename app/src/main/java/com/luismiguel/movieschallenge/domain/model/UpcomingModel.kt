package com.luismiguel.movieschallenge.domain.model

data class UpcomingModel(
    val dates: DatesModel,
    val page: Int,
    val results: List<MovieModel>,
    val totalpages: Int,
    val totalresults: Int
)