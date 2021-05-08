package com.dicoding.sample.academy.data

import com.dicoding.sample.academy.data.source.remote.RemoteDataSource
import com.dicoding.sample.academy.utils.DataDummy
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class AcademyRepositoryTest{
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponse[0].id
    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses(){
        `when`(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntities = repository.getAllCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse(){
        `when`(remote.getModules(courseId)).thenReturn(moduleResponse)
        val moduleEntities = repository.getAllModulesByCourse(courseId)
        verify(remote).getModules(courseId)
        assertNotNull(moduleEntities)
        assertEquals(moduleResponse.size.toLong(), moduleEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses(){
        `when`(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntities = repository.getBookmarkedCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent(){
        `when`(remote.getModules(courseId)).thenReturn(moduleResponse)
        `when`(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = repository.getContent(courseId, moduleId)
        verify(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content.content, resultModule.contentEntity?.content)
    }

    @Test
    fun getCoursesWithModule(){
        `when`(remote.getAllCourses()).thenReturn(courseResponse)
        val resultCourse = repository.getCourseWithModules(courseId)
        verify(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponse[0].title, resultCourse.title)
    }
}