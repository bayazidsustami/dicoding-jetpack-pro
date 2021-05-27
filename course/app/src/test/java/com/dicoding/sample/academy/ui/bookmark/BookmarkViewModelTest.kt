package com.dicoding.sample.academy.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.data.source.AcademyRepository
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
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Mock
    private lateinit var repository: AcademyRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<CourseEntity>>

    @Mock
    private lateinit var pagedList: PagedList<CourseEntity>

    @Before
    fun setup(){
        viewModel = BookmarkViewModel(repository)
    }

    @Test
    fun getBookmarks() {
        val dummyCourses = pagedList
        `when`(dummyCourses.size).thenReturn(5)

        val courses = MutableLiveData<PagedList<CourseEntity>>()
        courses.value = dummyCourses

        `when`(repository.getBookmarkedCourses()).thenReturn(courses)
        val courseEntity = viewModel.getBookmarks().value
        verify(repository).getBookmarkedCourses()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity?.size)

        viewModel.getBookmarks().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}