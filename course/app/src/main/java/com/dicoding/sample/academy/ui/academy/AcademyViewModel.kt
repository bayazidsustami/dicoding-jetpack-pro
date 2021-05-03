package com.dicoding.sample.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.dicoding.sample.academy.data.CourseEntity
import com.dicoding.sample.academy.utils.DataDummy

class AcademyViewModel: ViewModel() {
    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}