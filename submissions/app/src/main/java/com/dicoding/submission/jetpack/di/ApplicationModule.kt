package com.dicoding.submission.jetpack.di

import android.content.Context
import androidx.room.Room
import com.dicoding.submission.jetpack.data.dataSource.FilmRepository
import com.dicoding.submission.jetpack.data.dataSource.TvShowRepository
import com.dicoding.submission.jetpack.data.dataSource.local.FilmLocalDataSourceImpl
import com.dicoding.submission.jetpack.data.dataSource.local.LocalDataSource
import com.dicoding.submission.jetpack.data.dataSource.local.TvShowLocalDataSourceImpl
import com.dicoding.submission.jetpack.data.dataSource.local.room.FilmDao
import com.dicoding.submission.jetpack.data.dataSource.local.room.FilmExplorerDatabase
import com.dicoding.submission.jetpack.data.dataSource.local.room.TvShowDao
import com.dicoding.submission.jetpack.data.dataSource.remote.RemoteDataSource
import com.dicoding.submission.jetpack.data.dataSource.remote.filmDataSource.FilmDataSourceImpl
import com.dicoding.submission.jetpack.data.dataSource.remote.tvShowDataSource.TvShowDataSourceImpl
import com.dicoding.submission.jetpack.network.ApiBuilder
import com.dicoding.submission.jetpack.network.ApiService
import com.dicoding.submission.jetpack.ui.mainUI.detail.movie.DetailMovieViewModel
import com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow.DetailTvShowViewModel
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieAdapter
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieFavoriteViewModel
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieViewModel
import com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow.TvShowAdapter
import com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow.TvShowFavoriteViewModel
import com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow.TvShowViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ApplicationModule {
    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { MovieFavoriteViewModel(get()) }
        viewModel { DetailMovieViewModel(get()) }
        viewModel { TvShowViewModel(get()) }
        viewModel { DetailTvShowViewModel(get()) }
        viewModel { TvShowFavoriteViewModel(get()) }
    }

    val repositoryModule = module {
        single {
            FilmRepository(get(), get(), get())
        }
        single { TvShowRepository(get(), get(), get()) }
    }

    val adapterModule = module {
        factory { MovieAdapter() }
        factory { TvShowAdapter() }
    }

    val dataSourceModule = module {
        fun provideMovieRemoteDataSource(apiService: ApiService): RemoteDataSource.FilmDataSource{
            return FilmDataSourceImpl(apiService)
        }
        fun provideTvRemoteDataSource(apiService: ApiService): RemoteDataSource.TvShowDataSource{
            return TvShowDataSourceImpl(apiService)
        }

        fun provideMovieLocalDataSource(filmDao: FilmDao): LocalDataSource.FilmDataSource{
            return FilmLocalDataSourceImpl(filmDao)
        }

        fun provideTvLocalDataSource(tvShowDao: TvShowDao): LocalDataSource.TvShowDataSource{
            return TvShowLocalDataSourceImpl(tvShowDao)
        }

        single { provideMovieRemoteDataSource(get()) }
        single { provideTvRemoteDataSource(get()) }
        single { provideMovieLocalDataSource(get()) }
        single { provideTvLocalDataSource(get()) }
    }

    val databaseModule = module {
        fun provideAppDatabase(context: Context): FilmExplorerDatabase{
            return Room.databaseBuilder(
                context,
                FilmExplorerDatabase::class.java,
                "movie.db"
            ).build()
        }

        fun provideFilmDao(database: FilmExplorerDatabase): FilmDao{
            return database.filmDao()
        }

        fun provideTvDao(database: FilmExplorerDatabase): TvShowDao{
            return database.tvShowDao()
        }

        single { provideAppDatabase(androidApplication()) }
        single { provideFilmDao(get()) }
        single { provideTvDao(get()) }

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