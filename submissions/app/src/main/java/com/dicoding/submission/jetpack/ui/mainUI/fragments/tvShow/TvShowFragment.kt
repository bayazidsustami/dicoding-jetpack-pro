package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.databinding.FragmentTvBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment
import com.dicoding.submission.jetpack.ui.mainUI.OnItemClickListener
import com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow.DetailTvShowActivity

class TvShowFragment: BaseFragment<FragmentTvBinding>(
    FragmentTvBinding::inflate
) {
    private val viewModel: TvShowViewModel by viewModels()

    override fun initializeView(bind: FragmentTvBinding) {
        val list = viewModel.getListTvShows()
        val tvAdapter = TvShowAdapter(list)
        with(bind.rvTvShowList){
            adapter = tvAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        tvAdapter.setOnItemClickListener(object : OnItemClickListener<TvShowsEntity>{
            override fun onClick(data: TvShowsEntity) {
                val intent = Intent(requireContext(), DetailTvShowActivity::class.java).apply {
                    putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data)
                }
                startActivity(intent)
            }
        })
    }
}