package com.dicoding.sample.academy.data.source

import androidx.lifecycle.LiveData
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.data.entity.CourseWithModule
import com.dicoding.sample.academy.data.entity.ModuleEntity
import com.dicoding.sample.academy.vo.Resource

interface AcademyDataSource {
    fun getAllCourses(): LiveData<Resource<List<CourseEntity>>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>

    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>

    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>

    fun setCourseBookmark(course: CourseEntity, state: Boolean)

    fun setReadModule(module: ModuleEntity)
}