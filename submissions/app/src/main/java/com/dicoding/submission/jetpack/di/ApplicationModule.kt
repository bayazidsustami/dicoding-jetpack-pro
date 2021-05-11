package com.dicoding.submission.jetpack.di

import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.dataSource.remote.filmDataSource.FilmDataSourceImpl
import com.dicoding.submission.jetpack.network.ApiBuilder
import com.dicoding.submission.jetpack.network.ApiService
import com.dicoding.submission.jetpack.ui.mainUI.detail.movie.DetailMovieViewModel
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieAdapter
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ApplicationModule {
    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { DetailMovieViewModel(get()) }
    }

    val repositoryModule = module {
        single {
            FilmRepository(get(), get())
        }
    }

    val movieAdapterModule = module {
        single {
            MovieAdapter()
        }
    }

    val dataSourceModule = module {
        fun provideMovieDataSource(apiService: ApiService): FilmDataSourceImpl{
            return FilmDataSourceImpl(apiService)
        }
        single { provideMovieDataSource(get()) }
    }

    val coroutineScopeModule = module {
        single { CoroutineScope(Dispatchers.IO) }
    }

    val networkModule = module {
        fun provideNetworkConfig(): ApiService {
            return ApiBuilder.createService()
        }
        single { provideNetworkConfig() }
    }
}