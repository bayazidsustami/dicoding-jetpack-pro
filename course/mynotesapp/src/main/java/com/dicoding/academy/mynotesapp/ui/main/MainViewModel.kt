package com.dicoding.academy.mynotesapp.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.academy.mynotesapp.database.Note
import com.dicoding.academy.mynotesapp.repository.NoteRepository

class MainViewModel(application: Application): ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(sort: String): LiveData<PagedList<Note>>
        = LivePagedListBuilder(mNoteRepository.getAllNotes(sort), 10).build()
}