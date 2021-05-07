package com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow

import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.databinding.ActivityDetailTvShowBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseActivity
import com.dicoding.submission.jetpack.utils.Commons
import com.dicoding.submission.jetpack.utils.circularProgress

class DetailTvShowActivity : BaseActivity<ActivityDetailTvShowBinding>(
    ActivityDetailTvShowBinding::inflate
){
    private val viewModel: DetailTvShowViewModel by viewModels()

    override fun initializeView(bind: ActivityDetailTvShowBinding) {
        val intent = intent.getParcelableExtra<TvShowsEntity>(EXTRA_TV_SHOW)
        if (intent != null){
            viewModel.setSelectedTvShow(intent.id)

            supportActionBar?.run {
                title = intent.title
                setDisplayHomeAsUpEnabled(true)
            }

            val detail = viewModel.getDetailTvShow()
            bind.run {
                viewPoster.tvTitle.text = detail.title
                Glide.with(this@DetailTvShowActivity)
                    .load(detail.posterPath)
                    .placeholder(this@DetailTvShowActivity.circularProgress())
                    .into(bind.viewPoster.imgPoster)
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
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

}