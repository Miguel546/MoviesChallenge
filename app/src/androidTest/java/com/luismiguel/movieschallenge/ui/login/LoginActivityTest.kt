package com.luismiguel.movieschallenge.ui.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.luismiguel.movieschallenge.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun loginCorrectTest() {
        val tieUsuario = Espresso.onView(
            withId(R.id.tieUsuario)
        )
            .perform(
                ViewActions.typeText("Admin"), ViewActions.closeSoftKeyboard()
            )
        tieUsuario.check(ViewAssertions.matches(ViewMatchers.withText("Admin")))

        val tieContrasenia = Espresso.onView(
            withId(R.id.tieContrasenia)
        )
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("Password*123"), ViewActions.closeSoftKeyboard())
        tieContrasenia.check(ViewAssertions.matches(ViewMatchers.withText("Password*123")))

        val btnLogin = Espresso.onView(
            withId(R.id.btnLogin)
        )

        btnLogin.perform(ViewActions.click())

        runBlocking {
            delay(5000)
        }
    }

    @Test
    fun loginInCorrectTest() {

        val tieUsuario = Espresso.onView(
            withId(R.id.tieUsuario)
        )
            .perform(
                ViewActions.typeText("Admin"), ViewActions.closeSoftKeyboard()
            )
        tieUsuario.check(ViewAssertions.matches(ViewMatchers.withText("Admin")))

        val tieContrasenia = Espresso.onView(
            withId(R.id.tieContrasenia)
        )
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("Pass*"), ViewActions.closeSoftKeyboard())
        tieContrasenia.check(ViewAssertions.matches(ViewMatchers.withText("Pass*")))

        val btnLogin = Espresso.onView(
            withId(R.id.btnLogin)
        )

        btnLogin.perform(ViewActions.click())

        val aceptarButton = Espresso.onView(
            withId(android.R.id.button1)
        )
        aceptarButton.perform(ViewActions.click())

        runBlocking {
            delay(1000)
        }
    }
}