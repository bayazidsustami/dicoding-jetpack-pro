package com.dicoding.submission.jetpack.ui.mainUI.fragments

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun `get list movie`(){
        val movies = viewModel.getMovie()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}