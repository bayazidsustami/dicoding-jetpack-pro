package com.dicoding.sample.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.sample.academy.data.entity.CourseWithModule
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.vo.Resource

class DetailCourseViewModel(
    private val repository: AcademyRepository
): ViewModel() {
    var courseId = MutableLiveData<String>()

    fun setSelectedCourse(courseId: String){
        this.courseId.value = courseId
    }

    var courseModule: LiveData<Resource<CourseWithModule>> = Transformations.switchMap(courseId){mCourseId ->
        repository.getCourseWithModules(mCourseId)
    }

    fun setBookmark(){
        val moduleResource = courseModule.value
        if (moduleResource != null){
            val courseWithModule = moduleResource.data

            if (courseWithModule!= null){
                val courseEntity = courseWithModule.mCourse
                val newState = !courseEntity.bookmarked
                repository.setCourseBookmark(courseEntity, newState)
            }
        }
    }
}