package com.dicoding.sample.academy.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.utils.DataDummy
import com.dicoding.sample.academy.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {
    private lateinit var viewModel: AcademyViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<CourseEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<CourseEntity>

    @Before
    fun setup(){
        viewModel = AcademyViewModel(repository)
    }

    @Test
    fun getCourses() {
        val dummyCourse = Resource.success(pagedList)
        `when`(dummyCourse.data?.size).thenReturn(5)

        val courses = MutableLiveData<Resource<PagedList<CourseEntity>>>()
        courses.value = dummyCourse

        `when`(repository.getAllCourses()).thenReturn(courses)
        val courseEntities = viewModel.getCourses().value?.data
        verify(repository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }
}