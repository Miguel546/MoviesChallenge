package com.luismiguel.movieschallenge.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
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
class HomeActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun clickOneMovieTest() {
        runBlocking {
            delay(6000)
        }
        val recyclerView = Espresso.onView(
                withId(R.id.recyclerView)
            )
        recyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        recyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        runBlocking {
            delay(2000)
        }
    }

    @Test
    fun clickOneMovieBDTest() {
        runBlocking {
            delay(6000)
        }
        val recyclerView = Espresso.onView(
            withId(R.id.recyclerView)
        )
        recyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        recyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        runBlocking {
            delay(2000)
        }

        Espresso.pressBack()

        runBlocking {
            delay(1000)
        }

        val menu = Espresso.onView(
            withId(R.id.menuHamburger)
        )

        menu.perform(ViewActions.click())

        val guardadas = Espresso.onView(
            withId(R.id.menu_two)
        )

        guardadas.perform(ViewActions.click())

        runBlocking {
            delay(1000)
        }

        val rvGuardadas = Espresso.onView(
            withId(R.id.recyclerViewMS)
        )

        rvGuardadas.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        rvGuardadas.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        runBlocking {
            delay(2000)
        }
    }
}