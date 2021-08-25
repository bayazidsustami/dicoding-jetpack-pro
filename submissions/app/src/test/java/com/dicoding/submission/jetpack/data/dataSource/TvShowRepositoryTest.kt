package com.dicoding.submission.jetpack.data.dataSource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.submission.jetpack.PagedListUtils
import com.dicoding.submission.jetpack.TestCoroutineRule
import com.dicoding.submission.jetpack.data.dataSource.local.LocalDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.tvShowDataSource.TvShowDataSourceImpl
import com.dicoding.submission.jetpack.data.fakeRepository.FakeTvShowRepository
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.utils.DataDummy
import com.dicoding.submission.jetpack.utils.DataDummy.BASE_POSTER_PATH
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
@RunWith(MockitoJUnitRunner.Silent::class)
class TvShowRepositoryTest{
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource.TvShowDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource.TvShowDataSource

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var repository: FakeTvShowRepository

    @Before
    fun setup(){
        repository = FakeTvShowRepository(remoteDataSource, localDataSource, coroutineScope)
    }

    @Test
    fun `get all tvShows`() = testCoroutineRule.runBlockingTest {
        val dataSourceFactory = mock(DataSource.Factory::class.java)

        `when`(localDataSource.getListTv()).thenReturn(dataSourceFactory as DataSource.Factory<Int, TvShowsEntity>?)
        repository.getDiscoverTv()

        val data = Result.Success(data = PagedListUtils.mockPagedList(DataDummy.generateListTvShow()))

        verify(localDataSource).getListTv()
        assertNotNull(data)
        assertEquals(DataDummy.generateListTvShow().size, data.data.size)
    }

    @Test
    fun `get detail tv show`() = testCoroutineRule.runBlockingTest {
        val dataDummy = DataDummy.generateDetailTvShow()[0]
        val results = MutableLiveData<DetailTvShowsEntity>()
        results.value = dataDummy

        `when`(localDataSource.getDetailTv(anyString())).thenReturn(results)

        val data = LiveDataTestUtils.getValue(repository.getDetailTv(anyString()))
        val result = data as Result.Success<DetailTvShowsEntity>
        verify(localDataSource).getDetailTv(anyString())
        assertNotNull(data)
        assertEquals(dataDummy.posterPath, result.data.posterPath)
        assertEquals(dataDummy.id, result.data.id)
        assertEquals(dataDummy.title, result.data.title)
        assertEquals(dataDummy.status, result.data.status)
        assertEquals(dataDummy.episodeCount, result.data.episodeCount)
        assertEquals(dataDummy.overview, result.data.overview)
    }

    @Test
    fun `get favorite tv Show`() = testCoroutineRule.runBlockingTest {
        val dataSourceFactory = mock(DataSource.Factory::class.java)

        `when`(localDataSource.getListFavoriteTv()).thenReturn(dataSourceFactory as DataSource.Factory<Int, TvShowsEntity>?)
        repository.getFavoriteTvShow()

        val data = PagedListUtils.mockPagedList(DataDummy.generateListTvShow())

        verify(localDataSource).getListFavoriteTv()
        assertNotNull(data)
        assertEquals(DataDummy.generateListTvShow().size, data.size)
    }
}