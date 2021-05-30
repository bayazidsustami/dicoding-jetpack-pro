package com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow

import android.widget.Toast
import com.bumptech.glide.Glide
import com.dicoding.submission.jetpack.data.tvShows.DetailTvShowsEntity
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.databinding.ActivityDetailTvShowBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseActivity
import com.dicoding.submission.jetpack.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvShowActivity : BaseActivity<ActivityDetailTvShowBinding>(
    ActivityDetailTvShowBinding::inflate
){
    private val viewModel: DetailTvShowViewModel by viewModel()

    override fun initializeView(bind: ActivityDetailTvShowBinding) {
        val intent = intent.getParcelableExtra<TvShowsEntity>(EXTRA_TV_SHOW)
        if (intent != null){
            viewModel.setSelectedTvShow(intent.id)

            supportActionBar?.run {
                title = intent.title
                setDisplayHomeAsUpEnabled(true)
            }

            viewModel.tvDetail.observe(this){result ->
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
                        showDetail(result.data)
                    }
                }
            }

        }
    }

    private fun showDetail(detail: DetailTvShowsEntity){
        binding.run {
            viewPoster.tvTitle.text = detail.title
            Glide.with(this@DetailTvShowActivity)
                .load(detail.posterPath)
                .placeholder(this@DetailTvShowActivity.circularProgress())
                .into(binding.viewPoster.imgPoster)
            viewDetail.tvTitle.text = detail.title
            viewDetail.tvStatus.text = detail.status
            viewDetail.tvEpisode.text = detail.episodeCount

            tvDescription.text = detail.overview
            tvHomePage.text = detail.homepage

            tvHomePage.setOnClickListener {
                Commons.openBrowser(this@DetailTvShowActivity, detail.homepage)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

}