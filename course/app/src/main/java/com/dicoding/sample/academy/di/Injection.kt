package com.dicoding.sample.academy.di

import android.content.Context
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.data.source.remote.RemoteDataSource
import com.dicoding.sample.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository{
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return AcademyRepository.getInstance(remoteDataSource)
    }
}