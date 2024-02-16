package com.luismiguel.movieschallenge.utils

import android.content.SharedPreferences

fun SharedPreferences.getString(key: String): String? {
    return getString(key, null)
}

fun SharedPreferences.putString(key: String, value: String?) {
    edit().putString(key, value).apply()
}

fun textTvTotal(paginas: Int): String = "Páginas del 1 al $paginas"

fun textTvErrorUpcoming(error: String): String = "$error\n\n${Constants.NOT_DEFINED_MOVIES}"

fun textTvTitleDetail(title: String): String = "Title: $title"

fun textVoteAverageDetail(vote: String): String = "Vote average: $vote"

fun textReleaseDetail(release: String): String = "Release date: $release"

fun textOverviewDetail(overview: String): String = "Overview:\n\n$overview"

fun textTvTotalUpcoming(maxPaginas: String): String = "Páginas del 1 al $maxPaginas"

fun textTvPagActualUpcoming(paginaActual: String): String = "Página $paginaActual"