package com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.utils.DataDummy
import com.dicoding.submission.jetpack.utils.Result
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<Result<DetailTvShowsEntity>>

    private val tvId = DataDummy.generateListTvShow()[5].id
    private val tvShow = DataDummy.generateDetailTvShow()[5]

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(repository)
        viewModel.setSelectedTvShow(tvId)
    }

    @Test
    fun `loading getDetail tvShow`(){
        val result = MutableLiveData<Result<DetailTvShowsEntity>>()
        result.value = Result.Loading(data = null)

        val loadingState = Result.Loading(data = null)

        `when`(repository.getDetailTv(tvId)).thenReturn(result)
        val details = viewModel.getDetailTvShow().value
        verify(repository).getDetailTv(tvId)
        assertNotNull(details)
        assertEquals(loadingState, details)

        viewModel.getDetailTvShow().observeForever(observer)
        verify(observer).onChanged(loadingState)
    }

    @Test
    fun `error getDetail tvShow`(){
        val result = MutableLiveData<Result<DetailTvShowsEntity>>()
        result.value = Result.Error(data = null, message = "Error")

        val errorState = Result.Error(data = null, message = "Error")

        `when`(repository.getDetailTv(tvId)).thenReturn(result)
        val details = viewModel.getDetailTvShow().value
        verify(repository).getDetailTv(tvId)
        assertNotNull(details)
        assertEquals(errorState, details)

        viewModel.getDetailTvShow().observeForever(observer)
        verify(observer).onChanged(errorState)
    }



    @Test
    fun `success getDetail tvShow`(){
        val result = MutableLiveData<Result<DetailTvShowsEntity>>()
        result.value = Result.Success(data = tvShow)

        val successState = Result.Success(data = tvShow)

        `when`(repository.getDetailTv(tvId)).thenReturn(result)
        val detailData = viewModel.getDetailTvShow().value
        val details = detailData as Result.Success<DetailTvShowsEntity>

        assertNotNull(details)
        assertEquals(tvShow.id, details.data.id)
        assertEquals(tvShow.homepage, details.data.homepage)
        assertEquals(tvShow.overview, details.data.overview)
        assertEquals(tvShow.posterPath, details.data.posterPath)
        assertEquals(tvShow.episodeCount, details.data.episodeCount)
        assertEquals(tvShow.title, details.data.title)
        assertEquals(tvShow.status, details.data.status)

        viewModel.getDetailTvShow().observeForever(observer)
        verify(observer).onChanged(successState)
    }
}