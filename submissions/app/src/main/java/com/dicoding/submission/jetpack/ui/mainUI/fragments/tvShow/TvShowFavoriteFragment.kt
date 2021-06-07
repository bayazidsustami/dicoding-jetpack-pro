package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.databinding.FragmentTvBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment
import com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow.DetailTvShowActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment: BaseFragment<FragmentTvBinding>(
    FragmentTvBinding::inflate
) {
    private val viewModel: TvShowFavoriteViewModel by viewModel()
    private val tvAdapter: TvShowAdapter by inject()

    override fun initializeView(bind: FragmentTvBinding) {
        with(bind.rvTvShowList){
            adapter = tvAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            hasFixedSize()
        }

        viewModel.getMovie().observe(viewLifecycleOwner){data ->
            tvAdapter.submitList(data)
        }

        tvAdapter.onItemSelected = {data ->
            val intent = Intent(requireContext(), DetailTvShowActivity::class.java).apply {
                putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data)
            }
            startActivity(intent)
        }
    }
}