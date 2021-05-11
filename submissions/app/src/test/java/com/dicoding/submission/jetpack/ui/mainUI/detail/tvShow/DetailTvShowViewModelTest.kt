package com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow

import com.dicoding.submission.jetpack.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel

    private val tvId = DataDummy.generateListTvShow()[5].id
    private val tvShow = DataDummy.generateDetailTvShow()[5]

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
        viewModel.setSelectedTvShow(tvId)
    }

    @Test
    fun `get list tvShow detail test`(){
        val details = viewModel.getDetailTvShow()
        assertNotNull(details)
        assertEquals(tvShow.id, details.id)
        assertEquals(tvShow.homepage, details.homepage)
        assertEquals(tvShow.overview, details.overview)
        assertEquals(tvShow.posterPath, details.posterPath)
        assertEquals(tvShow.episodeCount, details.episodeCount)
        assertEquals(tvShow.title, details.title)
        assertEquals(tvShow.status, details.status)
    }
}