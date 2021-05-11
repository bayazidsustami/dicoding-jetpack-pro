package com.dicoding.submission.jetpack.ui.mainUI.detail.movie

import com.dicoding.submission.jetpack.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyData = DataDummy.generateMovieData()[2]
    private val movieId = dummyData.id

    private val dataDetail = DataDummy.generateDetailMovie()[2]

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setMovieSelected(movieId)
    }

    @Test
    fun `get detail view model list`(){
        viewModel.setMovieSelected(movieId)
        val movie = viewModel.getDetailMovie()
        assertNotNull(movie)
        assertEquals(dataDetail.id, movie.id)
        assertEquals(dataDetail.title, movie.title)
        assertEquals(dataDetail.status, movie.status)
        assertEquals(dataDetail.homepage, movie.homepage)
    }
}