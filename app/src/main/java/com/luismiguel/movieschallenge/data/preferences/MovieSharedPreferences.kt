package com.luismiguel.movieschallenge.data.preferences

import com.luismiguel.movieschallenge.utils.getString
import com.luismiguel.movieschallenge.utils.putString
import android.content.SharedPreferences
import javax.inject.Inject

class MovieSharedPreferences @Inject constructor(private val preferences: SharedPreferences) {
    companion object {
        const val KEY_USER = "user"
    }

    fun saveUser(user: String) {
        this.user = user
    }

    var user: String?
        get() = preferences.getString(KEY_USER).orEmpty()
        set(value) = preferences.putString(KEY_USER, value)

    fun clear() {
        preferences.edit().clear().apply()
    }
}