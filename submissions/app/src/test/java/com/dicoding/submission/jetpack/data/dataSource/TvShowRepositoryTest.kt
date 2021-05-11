package com.dicoding.submission.jetpack.data.dataSource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.submission.jetpack.TestCoroutineRule
import com.dicoding.submission.jetpack.data.dataSource.remote.response.details.tvShow.DetailTvShowResponse
import com.dicoding.submission.jetpack.data.dataSource.remote.tvShowDataSource.TvShowDataSourceImpl
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.utils.DataDummy.BASE_POSTER_PATH
import com.dicoding.submission.jetpack.utils.DummyResponse
import com.dicoding.submission.jetpack.utils.LiveDataTestUtils
import com.dicoding.submission.jetpack.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowRepositoryTest{
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var dataSource: TvShowDataSourceImpl

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var repository: TvShowRepository

    @Before
    fun setup(){
        repository = TvShowRepository(dataSource, coroutineScope)
    }

    @Test
    fun `get all tvShows`() = testCoroutineRule.runBlockingTest {
        val flow = flow {
            emit(DummyResponse.getDummyListTvShow())
        }

        `when`(dataSource.getDiscoverTv()).thenReturn(flow)
        val data = LiveDataTestUtils.getValue(repository.getDiscoverTv())
        val dataResult = data as Result.Success<List<TvShowsEntity>>
        verify(dataSource).getDiscoverTv()
        assertNotNull(data)
        assertEquals(DummyResponse.getDummyListTvShow().results?.size, dataResult.data.size)
    }

    @Test
    fun `get detail tv show`() = testCoroutineRule.runBlockingTest {
        val flow = flow {
            emit(DummyResponse.getDummyDetailTvShow())
        }

        `when`(dataSource.getDetailTv(anyString())).thenReturn(flow)

        val data = LiveDataTestUtils.getValue(repository.getDetailTv(anyString()))
        val result = data as Result.Success<DetailTvShowsEntity>
        verify(dataSource).getDetailTv(anyString())
        assertNotNull(data)
        assertEquals(BASE_POSTER_PATH+DummyResponse.getDummyDetailTvShow().posterPath, result.data.posterPath)
        assertEquals(DummyResponse.getDummyDetailTvShow().id.toString(), result.data.id)
        assertEquals(DummyResponse.getDummyDetailTvShow().originalName, result.data.title)
        assertEquals(DummyResponse.getDummyDetailTvShow().status, result.data.status)
        assertEquals(DummyResponse.getDummyDetailTvShow().numberOfEpisodes.toString(), result.data.episodeCount)
        assertEquals(DummyResponse.getDummyDetailTvShow().overview, result.data.overview)
    }
}