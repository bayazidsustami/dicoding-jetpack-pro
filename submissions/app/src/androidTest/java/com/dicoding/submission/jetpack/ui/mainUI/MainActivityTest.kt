package com.dicoding.submission.jetpack.ui.mainUI

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dicoding.submission.jetpack.R
import com.dicoding.submission.jetpack.utils.DataDummy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    private val listMovie = DataDummy.generateMovieData()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMoviesTest(){
        onView(withId(R.id.rvMovieList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieList)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(listMovie.size))
    }
}