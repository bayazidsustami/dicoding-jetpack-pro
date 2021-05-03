package com.dicoding.sample.academy.ui.bookmark

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setup(){
        viewModel = BookmarkViewModel()
    }

    @Test
    fun getBookmarks() {
        val courseEntity = viewModel.getBookmarks()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity.size)
    }
}