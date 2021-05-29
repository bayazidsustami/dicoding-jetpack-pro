package com.dicoding.submission.jetpack.di

import android.content.Context
import androidx.room.Room
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.dataSource.local.room.FilmExplorerDatabase
import com.dicoding.submission.jetpack.data.dataSource.remote.filmDataSource.FilmDataSourceImpl
import com.dicoding.submission.jetpack.data.dataSource.remote.tvShowDataSource.TvShowDataSourceImpl
import com.dicoding.submission.jetpack.network.ApiBuilder
import com.dicoding.submission.jetpack.network.ApiService
import com.dicoding.submission.jetpack.ui.mainUI.detail.movie.DetailMovieViewModel
import com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow.DetailTvShowViewModel
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieAdapter
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieViewModel
import com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow.TvShowAdapter
import com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow.TvShowViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ApplicationModule {
    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { DetailMovieViewModel(get()) }
        viewModel { TvShowViewModel(get()) }
        viewModel { DetailTvShowViewModel(get()) }
    }

    val repositoryModule = module {
        single {
            FilmRepository(get(), get())
        }
        single { TvShowRepository(get(), get()) }
    }

    val adapterModule = module {
        single { MovieAdapter() }
        single { TvShowAdapter() }
    }

    val dataSourceModule = module {
        fun provideMovieDataSource(apiService: ApiService): FilmDataSourceImpl{
            return FilmDataSourceImpl(apiService)
        }
        fun provideTvDataSource(apiService: ApiService): TvShowDataSourceImpl{
            return TvShowDataSourceImpl(apiService)
        }
        single { provideMovieDataSource(get()) }
        single { provideTvDataSource(get()) }
    }

    val databaseModule = module {
        fun provideAppDatabase(context: Context): FilmExplorerDatabase{
            return Room.databaseBuilder(
                context,
                FilmExplorerDatabase::class.java,
                "movie.db"
            ).build()
        }

        single { provideAppDatabase(androidApplication()) }
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