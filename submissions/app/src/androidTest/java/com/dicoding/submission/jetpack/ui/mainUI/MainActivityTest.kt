package com.dicoding.submission.jetpack.ui.mainUI

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
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
    private val detailMovie = DataDummy.generateDetailMovie()[0]

    private val listTv = DataDummy.generateListTvShow()
    private val detailTvShow = DataDummy.generateDetailTvShow()[0]

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMoviesTest(){
        onView(withId(R.id.rvMovieList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieList)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(listMovie.size))
    }

    @Test
    fun loadTvShowTest(){
        onView(withText(R.string.tvShow)).perform(click())
        onView(withId(R.id.rvTvShowList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShowList)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(listTv.size))
    }

    @Test
    fun loadMovieDetailTest(){
        onView(withId(R.id.rvMovieList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.viewPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailMovie.status)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(detailMovie.overview)))
        onView(withId(R.id.tvHomePage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHomePage)).check(matches(withText(detailMovie.homepage)))
    }

    @Test
    fun loadTvShowDetailTest(){
        onView(withText(R.string.tvShow)).perform(click())
        onView(withId(R.id.rvTvShowList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShowList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.viewPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailTvShow.status)))
        onView(withId(R.id.tvEpisode)).check(matches(withText(detailTvShow.episodeCount)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(detailTvShow.overview)))
        onView(withId(R.id.tvHomePage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHomePage)).check(matches(withText(detailTvShow.homepage)))
    }
}