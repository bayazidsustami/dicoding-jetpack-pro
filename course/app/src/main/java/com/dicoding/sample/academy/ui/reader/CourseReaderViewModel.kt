package com.dicoding.sample.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sample.academy.data.entity.ModuleEntity
import com.dicoding.sample.academy.data.source.AcademyRepository

class CourseReaderViewModel(
    private val repository: AcademyRepository
): ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> = repository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): LiveData<ModuleEntity> = repository.getContent(courseId, moduleId)
}