package com.dicoding.submission.jetpack.ui.mainUI.detail.movie

import android.widget.Toast
import com.bumptech.glide.Glide
import com.dicoding.submission.jetpack.data.movie.DetailMovieEntity
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.databinding.ActivityDetailMovieBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseActivity
import com.dicoding.submission.jetpack.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>(
        ActivityDetailMovieBinding::inflate
) {
    private val viewModel: DetailMovieViewModel by viewModel()

    override fun initializeView(bind: ActivityDetailMovieBinding) {

        val intent = intent.getParcelableExtra<MoviesEntity>(EXTRA_DATA_MOVIE)

        intent?.id?.let { viewModel.setMovieSelected(it) }

        supportActionBar?.run {
            title = intent?.title
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel.getDetailMovie().observe(this){ result ->
            when(result){
                is Result.Loading -> {
                    bind.progressBar.visible()
                }
                is Result.Error -> {
                    bind.progressBar.gone()
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    bind.progressBar.gone()
                    bind.viewContent.visible()
                    showDetail(result.data, intent?.posterPath, intent?.releaseDate)
                }
            }
        }


    }

    private fun showDetail(
        dataDetail: DetailMovieEntity,
        urlImg: String?,
        releaseDate: String?){
        binding.run {
            viewPoster.run {
                Glide.with(this@DetailMovieActivity)
                    .load(urlImg)
                    .placeholder(this@DetailMovieActivity.circularProgress())
                    .into(imgPoster)
                tvTitle.text = dataDetail.title
            }
            viewDetail.run {
                tvTitle.text = dataDetail.title
                tvStatus.text = dataDetail.status
                tvDate.text = releaseDate
            }
            tvDescription.text = dataDetail.overview
            tvHomePage.text = dataDetail.homepage

            tvHomePage.setOnClickListener {
                Commons.openBrowser(this@DetailMovieActivity, dataDetail.homepage)
            }
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