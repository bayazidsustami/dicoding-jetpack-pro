package com.dicoding.sample.academy.ui.bookmark

import com.dicoding.sample.academy.data.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
