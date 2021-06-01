package com.dicoding.submission.jetpack.ui.mainUI

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dicoding.submission.jetpack.R
import com.dicoding.submission.jetpack.utils.DataDummy
import com.dicoding.submission.jetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    private val listMovie = DataDummy.generateMovieData()
    private val detailMovie = DataDummy.generateDetailMovie()[0]

    private val listTv = DataDummy.generateListTvShow()
    private val detailTvShow = DataDummy.generateDetailTvShow()[2]

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

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
        onView(withId(R.id.rvMovieList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.viewPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailMovie.status)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(detailMovie.overview)))
        onView(withId(R.id.tvHomePage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHomePage)).check(matches(withText(detailMovie.homepage)))
        onView(withId(R.id.tvHomePage)).perform(click())
    }

    @Test
    fun loadTvShowDetailTest(){
        onView(withText(R.string.tvShow)).perform(click())
        onView(withId(R.id.rvTvShowList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShowList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.viewPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailTvShow.status)))
        onView(withId(R.id.tvEpisode)).check(matches(withText(detailTvShow.episodeCount)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(detailTvShow.overview)))
        onView(withId(R.id.tvHomePage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHomePage)).check(matches(withText(detailTvShow.homepage)))
        onView(withId(R.id.tvHomePage)).perform(click())
    }

    @Test
    fun openFavoriteMovieListTest(){
        onView(withId(R.id.rvMovieList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.viewPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailMovie.status)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(detailMovie.overview)))
        onView(withId(R.id.tvHomePage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHomePage)).check(matches(withText(detailMovie.homepage)))

        onView(withId(R.id.is_favorite)).perform(click())
        onView(withContentDescription(androidx.appcompat.R.string.abc_action_bar_up_description)).perform(click())

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rvMovieList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.viewPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailMovie.status)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(detailMovie.overview)))
        onView(withId(R.id.tvHomePage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHomePage)).check(matches(withText(detailMovie.homepage)))
        onView(withId(R.id.tvHomePage)).perform(click())
    }

    @Test
    fun openFavoriteTvShowListTest(){
        onView(withText(R.string.tvShow)).perform(click())
        onView(withId(R.id.rvTvShowList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShowList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.viewPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailTvShow.status)))
        onView(withId(R.id.tvEpisode)).check(matches(withText(detailTvShow.episodeCount)))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDescription)).check(matches(withText(detailTvShow.overview)))
        onView(withId(R.id.tvHomePage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHomePage)).check(matches(withText(detailTvShow.homepage)))

        onView(withId(R.id.is_favorite)).perform(click())
        onView(withContentDescription(androidx.appcompat.R.string.abc_action_bar_up_description)).perform(click())

        onView(withId(R.id.navigation_favorite)).perform(click())
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
        onView(withId(R.id.tvHomePage)).perform(click())
    }
}