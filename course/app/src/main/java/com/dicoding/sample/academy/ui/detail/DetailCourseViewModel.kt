package com.dicoding.sample.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sample.academy.data.CourseEntity
import com.dicoding.sample.academy.data.ModuleEntity
import com.dicoding.sample.academy.data.source.AcademyRepository

class DetailCourseViewModel(
    private val repository: AcademyRepository
): ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String){
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> = repository.getCourseWithModules(courseId)

    fun getModules(): LiveData<List<ModuleEntity>> = repository.getAllModulesByCourse(courseId)
}