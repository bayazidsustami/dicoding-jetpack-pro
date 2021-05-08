package com.dicoding.sample.academy.ui.academy

import com.dicoding.sample.academy.data.CourseEntity
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {
    private lateinit var viewModel: AcademyViewModel

    @Mock
    private lateinit var repository: AcademyRepository

    @Before
    fun setup(){
        viewModel = AcademyViewModel(repository)
    }

    @Test
    fun getCourses() {
        `when`(repository.getAllCourses()).thenReturn(DataDummy.generateDummyCourses() as ArrayList<CourseEntity>)
        val courseEntities = viewModel.getCourses()
        verify(repository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}