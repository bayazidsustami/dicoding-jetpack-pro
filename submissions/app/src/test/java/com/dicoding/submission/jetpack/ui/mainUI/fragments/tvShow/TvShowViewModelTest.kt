package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.utils.Result
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<Result<PagedList<TvShowsEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowsEntity>


    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
    }

    @Test
    fun `loading get data tv shows`(){
        val result = MutableLiveData<Result<PagedList<TvShowsEntity>>>()
        result.value = Result.Loading(data = null)

        val loadingState = Result.Loading(data = null)
        `when`(repository.getDiscoverTv()).thenReturn(result)
        val tvShows = viewModel.getListTvShows().value
        verify(repository).getDiscoverTv()
        assertNotNull(tvShows)
        assertEquals(loadingState, tvShows)

        viewModel.getListTvShows().observeForever(observer)
        verify(observer).onChanged(loadingState)
    }

    @Test
    fun `error get data tv shows`(){
        val result = MutableLiveData<Result<PagedList<TvShowsEntity>>>()
        result.value = Result.Error(data = null, message = "Error")

        val errorState = Result.Error(data = null, message = "Error")

        `when`(repository.getDiscoverTv()).thenReturn(result)
        val tvShows = viewModel.getListTvShows().value
        verify(repository).getDiscoverTv()
        assertNotNull(tvShows)
        assertEquals(errorState, tvShows)

        viewModel.getListTvShows().observeForever(observer)
        verify(observer).onChanged(errorState)
    }

    @Test
    fun `success get data tv shows`(){
        val successState = Result.Success(data = pagedList)

        `when`(successState.data.size).thenReturn(5)

        val result = MutableLiveData<Result<PagedList<TvShowsEntity>>>()
        result.value = successState

        `when`(repository.getDiscoverTv()).thenReturn(result)
        val tvShows = viewModel.getListTvShows().value
        val listData = tvShows as Result.Success<PagedList<TvShowsEntity>>
        verify(repository).getDiscoverTv()
        assertNotNull(tvShows)
        assertEquals(5, listData.data.size)

        viewModel.getListTvShows().observeForever(observer)
        verify(observer).onChanged(successState)
    }
}