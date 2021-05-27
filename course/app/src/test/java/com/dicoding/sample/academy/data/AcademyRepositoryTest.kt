package com.dicoding.sample.academy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.sample.academy.PagedListUtils
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.data.entity.CourseWithModule
import com.dicoding.sample.academy.data.entity.ModuleEntity
import com.dicoding.sample.academy.data.source.local.LocalDataSource
import com.dicoding.sample.academy.data.source.remote.RemoteDataSource
import com.dicoding.sample.academy.utils.AppExecutors
import com.dicoding.sample.academy.utils.DataDummy
import com.dicoding.sample.academy.utils.LiveDataTestUtils
import com.dicoding.sample.academy.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class AcademyRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val repository = FakeAcademyRepository(remote, local, appExecutors)

    private val courseResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponse[0].id
    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses(){
        val dataSourceFactory = mock(DataSource.Factory::class.java)  as DataSource.Factory<Int, CourseEntity>
        `when`(local.getAllCourses()).thenReturn(dataSourceFactory)
        repository.getAllCourses()

        val courseEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyCourses()))
        verify(local).getAllCourses()
        assertNotNull(courseEntities.data)
        assertEquals(courseResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getAllModulesByCourse(){
        val dummyModules = MutableLiveData<List<ModuleEntity>>()
        dummyModules.value = DataDummy.generateDummyModules(courseId)
        `when`(local.getAllModulesByCourse(courseId)).thenReturn(dummyModules)

        val moduleEntities = LiveDataTestUtils.getValue(repository.getAllModulesByCourse(courseId))
        verify(local).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities.data)
        assertEquals(moduleResponse.size.toLong(), moduleEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedCourses(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CourseEntity>
        `when`(local.getBookmarkedCourses()).thenReturn(dataSourceFactory)
        repository.getBookmarkedCourses()
        val courseEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyCourses()))
        verify(local).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getContent(){
        val dummyEntity = MutableLiveData<ModuleEntity>()
        dummyEntity.value = DataDummy.generateDummyModuleWithContent(moduleId)
        `when`(local.getModuleWithContent(courseId)).thenReturn(dummyEntity)

        val resultContent = LiveDataTestUtils.getValue(repository.getContent(courseId))

        verify(local).getModuleWithContent(courseId)

        assertNotNull(resultContent)
        assertNotNull(resultContent.data?.contentEntity)
        assertNotNull(resultContent.data?.contentEntity?.content)
        assertEquals(content.content, resultContent.data?.contentEntity?.content)
    }

    @Test
    fun getCoursesWithModule(){
        val dummyEntity = MutableLiveData<CourseWithModule>()
        dummyEntity.value = DataDummy.generateDummyCourseWithModules(DataDummy.generateDummyCourses()[0], false)
        `when`(local.getCourseWithModules(courseId)).thenReturn(dummyEntity)

        val resultCourse = LiveDataTestUtils.getValue(repository.getCourseWithModules(courseId))
        verify(local).getCourseWithModules(courseId)

        assertNotNull(resultCourse.data)
        assertNotNull(resultCourse.data?.mCourse?.title)
        assertEquals(courseResponse[0].title, resultCourse.data?.mCourse?.title)
    }
}