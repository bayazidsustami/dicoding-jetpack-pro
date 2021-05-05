package com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow

import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.databinding.ActivityDetailTvShowBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseActivity

class DetailTvShowActivity : BaseActivity<ActivityDetailTvShowBinding>(
    ActivityDetailTvShowBinding::inflate
){
    private val viewModel: DetailTvShowViewModel by viewModels()

    override fun initializeView(bind: ActivityDetailTvShowBinding) {
        val intent = intent.getParcelableExtra<TvShowsEntity>(EXTRA_TV_SHOW)
        if (intent != null){
            viewModel.setSelectedTvShow(intent.id)

            val detail = viewModel.getDetailTvShow()
            bind.run {
                viewPoster.tvTitle.text = detail.title
                Glide.with(this@DetailTvShowActivity)
                    .load(detail.posterPath)
                    .into(bind.viewPoster.imgPoster)
                viewDetail.tvTitle.text = detail.title
                viewDetail.tvStatus.text = detail.status
                viewDetail.tvEpisode.text = detail.episodeCount

                tvDescription.text = detail.overview
                tvHomePage.text = detail.homepage
            }
        }
    }

    companion object{
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

}