package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.databinding.FragmentTvBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment

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
    }
}