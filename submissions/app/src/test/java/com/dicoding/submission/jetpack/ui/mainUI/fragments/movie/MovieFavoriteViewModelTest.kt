package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest{
    private lateinit var viewModel: MovieFavoriteViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: FilmRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Test
    fun `get favorite movie`(){
        viewModel = MovieFavoriteViewModel(repository)

        val pageResult = pagedList

        `when`(pageResult.size).thenReturn(5)

        val result = MutableLiveData<PagedList<MoviesEntity>>()
        result.value = pageResult

        `when`(repository.getFavoriteMovie()).thenReturn(result)

        val dataResult = viewModel.getMovie().value

        verify(repository).getFavoriteMovie()

        assertNotNull(dataResult)
        assertEquals(5, dataResult?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(pageResult)
    }
}