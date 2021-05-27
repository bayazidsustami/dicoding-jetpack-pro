package com.dicoding.sample.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.data.source.AcademyRepository

class BookmarkViewModel(
    private val repository: AcademyRepository
): ViewModel() {
    fun getBookmarks(): LiveData<PagedList<CourseEntity>>
        = repository.getBookmarkedCourses()

    fun setBookmark(courseEntity: CourseEntity){
        val newState = !courseEntity.bookmarked
        repository.setCourseBookmark(courseEntity, newState)
    }
}