package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
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
class TvShowFavoriteViewModelTest{
    private lateinit var viewModel: TvShowFavoriteViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowsEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowsEntity>

    @Before
    fun setup(){
        viewModel = TvShowFavoriteViewModel(repository)
    }

    @Test
    fun `get favorite tv shows`(){
        val pageResult = pagedList

        `when`(pageResult.size).thenReturn(5)

        val result = MutableLiveData<PagedList<TvShowsEntity>>()
        result.value = pageResult

        `when`(repository.getFavoriteTvShow()).thenReturn(result)

        val dataResult = viewModel.getMovie().value

        verify(repository).getFavoriteTvShow()

        assertNotNull(dataResult)
        assertEquals(5, dataResult?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(pageResult)
    }
}