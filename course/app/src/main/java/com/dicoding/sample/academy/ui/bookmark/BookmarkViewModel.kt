package com.dicoding.sample.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.dicoding.sample.academy.data.CourseEntity
import com.dicoding.sample.academy.utils.DataDummy

class BookmarkViewModel: ViewModel() {
    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}