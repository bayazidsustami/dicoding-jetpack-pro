package com.dicoding.sample.academy.di

import android.content.Context
import com.dicoding.sample.academy.data.source.AcademyRepository
import com.dicoding.sample.academy.data.source.local.LocalDataSource
import com.dicoding.sample.academy.data.source.local.room.AcademyDatabase
import com.dicoding.sample.academy.data.source.remote.RemoteDataSource
import com.dicoding.sample.academy.utils.AppExecutors
import com.dicoding.sample.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository{
        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()
        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}