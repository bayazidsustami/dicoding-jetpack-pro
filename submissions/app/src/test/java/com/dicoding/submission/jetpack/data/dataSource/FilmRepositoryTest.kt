package com.dicoding.submission.jetpack.data.dataSource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.submission.jetpack.PagedListUtils
import com.dicoding.submission.jetpack.TestCoroutineRule
import com.dicoding.submission.jetpack.data.dataSource.local.LocalDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.fakeRepository.FakeFilmRepository
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.utils.DataDummy
import com.dicoding.submission.jetpack.utils.LiveDataTestUtils
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class FilmRepositoryTest{

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource.FilmDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource.FilmDataSource

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var repository: FakeFilmRepository
    @Before
    fun setup(){
        repository = FakeFilmRepository(remoteDataSource,localDataSource, coroutineScope)
    }

    @Test
    fun `get all movies`() = testCoroutineRule.runBlockingTest {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>

        `when`(localDataSource.getListFilm()).thenReturn(dataSourceFactory)
        repository.getDiscoverMovie()

        val dataMovie = Result.Success(data = PagedListUtils.mockPagedList(DataDummy.generateMovieData()))
        verify(localDataSource).getListFilm()
        assertNotNull(dataMovie)
        assertEquals(DataDummy.generateMovieData().size, dataMovie.data.size)
    }

    @Test
    fun `get detail movie`() = testCoroutineRule.runBlockingTest {
        val results = MutableLiveData<DetailMovieEntity>()
        results.value = DataDummy.generateDetailMovie()[0]

        `when`(localDataSource.getDetailFilm(anyString())).thenReturn(results)

        val data = LiveDataTestUtils.getValue(repository.getDetailMovie(anyString()))
        val result = data as Result.Success<DetailMovieEntity>
        verify(localDataSource).getDetailFilm(anyString())
        assertNotNull(data)
        assertEquals(DataDummy.generateDetailMovie()[0].homepage, result.data.homepage)
        assertEquals(DataDummy.generateDetailMovie()[0].id, result.data.id)
        assertEquals(DataDummy.generateDetailMovie()[0].title, result.data.title)
        assertEquals(DataDummy.generateDetailMovie()[0].overview, result.data.overview)
        assertEquals(DataDummy.generateDetailMovie()[0].status, result.data.status)
    }

    @Test
    fun `get favorite movie test`() = testCoroutineRule.runBlockingTest {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>

        `when`(localDataSource.getListFilmFavorite()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovie()

        val dataMovie = PagedListUtils.mockPagedList(DataDummy.generateMovieData())
        verify(localDataSource).getListFilmFavorite()
        assertNotNull(dataMovie)
        assertEquals(DataDummy.generateMovieData().size, dataMovie.size)
    }
}