package com.dicoding.submission.jetpack

import android.app.Application
import com.dicoding.submission.jetpack.di.ApplicationModule.adapterModule
import com.dicoding.submission.jetpack.di.ApplicationModule.coroutineScopeModule
import com.dicoding.submission.jetpack.di.ApplicationModule.dataSourceModule
import com.dicoding.submission.jetpack.di.ApplicationModule.databaseModule
import com.dicoding.submission.jetpack.di.ApplicationModule.networkModule
import com.dicoding.submission.jetpack.di.ApplicationModule.repositoryModule
import com.dicoding.submission.jetpack.di.ApplicationModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FilmExplorerApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@FilmExplorerApp)
            modules(listOf(
                adapterModule,
                viewModelModule,
                repositoryModule,
                databaseModule,
                coroutineScopeModule,
                networkModule,
                dataSourceModule
            ))
        }
    }

}