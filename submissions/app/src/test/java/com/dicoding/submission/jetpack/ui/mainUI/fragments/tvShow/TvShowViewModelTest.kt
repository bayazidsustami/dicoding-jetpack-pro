package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun `get list tv show list test`(){
        val tvShows = viewModel.getListTvShows()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}