package com.dicoding.sample.academy.ui.bookmark

import com.dicoding.sample.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
