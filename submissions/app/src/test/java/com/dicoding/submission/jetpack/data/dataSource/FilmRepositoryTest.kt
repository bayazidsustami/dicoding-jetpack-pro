package com.dicoding.submission.jetpack.data.dataSource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.submission.jetpack.TestCoroutineRule
import com.dicoding.submission.jetpack.data.dataSource.remote.filmDataSource.FilmDataSourceImpl
import com.dicoding.submission.jetpack.data.fakeRepository.FakeFilmRepository
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.utils.DummyResponse
import com.dicoding.submission.jetpack.utils.LiveDataTestUtils
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
@RunWith(MockitoJUnitRunner::class)
class FilmRepositoryTest{

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var dataSource: FilmDataSourceImpl

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var repository: FakeFilmRepository
    @Before
    fun setup(){
        repository = FakeFilmRepository(dataSource, coroutineScope)
    }

    @Test
    fun `get all movies`() = testCoroutineRule.runBlockingTest {
        val flow = flow {
            emit(DummyResponse.getDummyListMovie())
        }

        `when`(dataSource.getDiscoverFilm()).thenReturn(flow)

        val data = LiveDataTestUtils.getValue(repository.getDiscoverMovie())
        val dataResult = data as Result.Success<List<MoviesEntity>>
        verify(dataSource).getDiscoverFilm()
        assertNotNull(data)
        assertEquals(DummyResponse.getDummyListMovie().results?.size, dataResult.data.size)
    }

    @Test
    fun `get detail movie`() = testCoroutineRule.runBlockingTest {
        val flow = flow {
            emit(DummyResponse.getDummyDetailMovie())
        }

        `when`(dataSource.getDetailFilm(anyString())).thenReturn(flow)

        val data = LiveDataTestUtils.getValue(repository.getDetailMovie(anyString()))
        val result = data as Result.Success<DetailMovieEntity>
        verify(dataSource).getDetailFilm(anyString())
        assertNotNull(data)
        assertEquals(DummyResponse.getDummyDetailMovie().homepage, result.data.homepage)
        assertEquals(DummyResponse.getDummyDetailMovie().id.toString(), result.data.id)
        assertEquals(DummyResponse.getDummyDetailMovie().title, result.data.title)
        assertEquals(DummyResponse.getDummyDetailMovie().overview, result.data.overview)
        assertEquals(DummyResponse.getDummyDetailMovie().status, result.data.status)
    }
}