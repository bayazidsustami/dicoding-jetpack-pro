package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun `get list movie test`(){
        val movies = viewModel.getMovie()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}