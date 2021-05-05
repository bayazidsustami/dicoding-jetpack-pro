package com.dicoding.submission.jetpack.ui.mainUI.detail.movie

import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.databinding.ActivityDetailMovieBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseActivity

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>(
        ActivityDetailMovieBinding::inflate
) {
    private val viewModel: DetailMovieViewModel by viewModels()
    override fun initializeView(bind: ActivityDetailMovieBinding) {
        val intent = intent.getParcelableExtra<MoviesEntity>(EXTRA_DATA_MOVIE)
        intent?.id?.let { viewModel.setMovieSelected(it) }

        val dataDetail = viewModel.getDetailMovie()

        supportActionBar?.run {
            title = intent?.title
            setDisplayHomeAsUpEnabled(true)
        }

        bind.run {
            viewPoster.run {
                Glide.with(this@DetailMovieActivity)
                    .load(intent?.posterPath)
                    .into(imgPoster)
                tvTitle.text = dataDetail.title
            }
            viewDetail.run {
                tvTitle.text = dataDetail.title
                tvStatus.text = dataDetail.status
                tvDate.text = intent?.releaseDate
            }
            tvDescription.text = dataDetail.overview
            tvHomePage.text = dataDetail.homepage
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val EXTRA_DATA_MOVIE = "extra_data_movie"
    }
}