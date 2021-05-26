package com.dicoding.sample.academy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.data.entity.CourseWithModule
import com.dicoding.sample.academy.data.entity.ModuleEntity
import com.dicoding.sample.academy.data.source.local.LocalDataSource
import com.dicoding.sample.academy.data.source.remote.RemoteDataSource
import com.dicoding.sample.academy.utils.AppExecutors
import com.dicoding.sample.academy.utils.DataDummy
import com.dicoding.sample.academy.utils.LiveDataTestUtils
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
        val dummyCourses = MutableLiveData<List<CourseEntity>>()
        dummyCourses.value = DataDummy.generateDummyCourses()
        `when`(local.getAllCourses()).thenReturn(dummyCourses)

        val courseEntities = LiveDataTestUtils.getValue(repository.getAllCourses())
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
        val dummyCourses = MutableLiveData<List<CourseEntity>>()
        dummyCourses.value = DataDummy.generateDummyCourses()
        `when`(local.getBookmarkedCourses()).thenReturn(dummyCourses)

        val courseEntities = LiveDataTestUtils.getValue(repository.getBookmarkedCourses())
        verify(local).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
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