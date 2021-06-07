package com.dicoding.submission.jetpack.ui.mainUI.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.utils.DataDummy
import com.dicoding.submission.jetpack.utils.LiveDataTestUtils
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Result<DetailMovieEntity>>

    private val dummyData = DataDummy.generateMovieData()[1]
    private val movieId = dummyData.id

    private val dataDetail = DataDummy.generateDetailMovie()[1]

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(repository)
        viewModel.setMovieSelected(movieId)
    }

    @Test
    fun `loading getDetail movie`(){
        val loadingState = Result.Loading(data = null)

        val result = MutableLiveData<Result<DetailMovieEntity>>()
        result.value = loadingState

        `when`(repository.getDetailMovie(movieId)).thenReturn(result)
        val details = LiveDataTestUtils.getValue(viewModel.detailMovie)
        verify(repository).getDetailMovie(movieId)
        assertNotNull(details)
        assertEquals(loadingState, details)

        viewModel.detailMovie.observeForever(observer)
        verify(observer).onChanged(loadingState)
    }

    @Test
    fun `error getDetail movie`(){
        val errorState = Result.Error(data = null, message = "Error")

        val result = MutableLiveData<Result<DetailMovieEntity>>()
        result.value = errorState

        `when`(repository.getDetailMovie(movieId)).thenReturn(result)
        val details = LiveDataTestUtils.getValue(viewModel.detailMovie)
        verify(repository).getDetailMovie(movieId)
        assertNotNull(details)
        assertEquals(errorState, details)

        viewModel.detailMovie.observeForever(observer)
        verify(observer).onChanged(errorState)
    }

    @Test
    fun `success get detail data movie test`(){
        val successState = Result.Success(data = dataDetail)

        val result = MutableLiveData<Result<DetailMovieEntity>>()
        result.value = successState

        `when`(repository.getDetailMovie(movieId)).thenReturn(result)
        val details = LiveDataTestUtils.getValue(viewModel.detailMovie)
        val detailData = details as Result.Success<DetailMovieEntity>

        verify(repository).getDetailMovie(movieId)
        assertNotNull(details)
        assertNotNull(detailData.data)
        assertEquals(dataDetail.id, detailData.data.id)
        assertEquals(dataDetail.homepage, detailData.data.homepage)
        assertEquals(dataDetail.overview, detailData.data.overview)
        assertEquals(dataDetail.status, detailData.data.status)
        assertEquals(dataDetail.title, detailData.data.title)

        viewModel.detailMovie.observeForever(observer)
        verify(observer).onChanged(successState)
    }
}
