package com.dicoding.sample.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.vo.Resource

class AcademyViewModel(
    private val repository: AcademyRepository
): ViewModel() {
    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> = repository.getAllCourses()
}