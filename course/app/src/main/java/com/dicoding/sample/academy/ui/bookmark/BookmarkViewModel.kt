package com.dicoding.sample.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sample.academy.data.CourseEntity
import com.dicoding.sample.academy.data.source.AcademyRepository

class BookmarkViewModel(
    private val repository: AcademyRepository
): ViewModel() {
    fun getBookmarks(): LiveData<List<CourseEntity>> = repository.getBookmarkedCourses()
}