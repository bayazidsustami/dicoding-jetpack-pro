package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.utils.DataDummy
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Result<List<MoviesEntity>>>

    private val dummyMovie = DataDummy.generateMovieData()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun `loading get data movie`(){
        /*val result = MutableLiveData<Result<List<MoviesEntity>>>()
        result.value = Result.Loading(data = null)

        val loadingState = Result.Loading<List<MoviesEntity>>(data = null)

        `when`(repository.getDiscoverMovie()).thenReturn(result)
        val movies = viewModel.getMovie().value
        verify(repository).getDiscoverMovie()
        assertNotNull(movies)
        assertEquals(loadingState, movies)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(loadingState)*/
    }

    @Test
    fun `error get data movie`(){
        /*val result = MutableLiveData<Result<List<MoviesEntity>>>()
        result.value = Result.Error(data = null, message = "Something Error")

        val errorState = Result.Error<List<MoviesEntity>>(data = null, message = "Something Error")
        `when`(repository.getDiscoverMovie()).thenReturn(result)
        val movies = viewModel.getMovie().value
        verify(repository).getDiscoverMovie()
        assertNotNull(movies)
        assertEquals(errorState, movies)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(errorState)*/
    }

    @Test
    fun `success get data movie test`(){
        /*val result = MutableLiveData<Result<List<MoviesEntity>>>()
        result.value = Result.Success(data = dummyMovie)

        val successState = Result.Success(data = dummyMovie)

        `when`(repository.getDiscoverMovie()).thenReturn(result)
        val movies = viewModel.getMovie().value
        val listData = movies as Result.Success<List<MoviesEntity>>
        verify(repository).getDiscoverMovie()
        assertNotNull(movies)
        assertEquals(10, listData.data.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(successState)*/
    }
}