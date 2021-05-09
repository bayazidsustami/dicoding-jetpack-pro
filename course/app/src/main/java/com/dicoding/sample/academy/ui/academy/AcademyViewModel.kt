package com.dicoding.sample.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sample.academy.data.CourseEntity
import com.dicoding.sample.academy.data.source.AcademyRepository

class AcademyViewModel(
    private val repository: AcademyRepository
): ViewModel() {
    fun getCourses(): LiveData<List<CourseEntity>> = repository.getAllCourses()
}