package com.dicoding.sample.academy.ui.bookmark

import com.dicoding.sample.academy.data.CourseEntity
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Mock
    private lateinit var repository: AcademyRepository

    @Before
    fun setup(){
        viewModel = BookmarkViewModel(repository)
    }

    @Test
    fun getBookmarks() {
        `when`(repository.getBookmarkedCourses()).thenReturn(DataDummy.generateDummyCourses() as ArrayList<CourseEntity>)
        val courseEntity = viewModel.getBookmarks()
        verify(repository).getBookmarkedCourses()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity.size)
    }
}