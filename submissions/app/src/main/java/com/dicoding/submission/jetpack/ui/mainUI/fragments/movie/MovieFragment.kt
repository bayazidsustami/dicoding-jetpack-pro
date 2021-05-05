package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.databinding.FragmentMovieBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment

class MovieFragment: BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {
    private val viewModel: MovieViewModel by viewModels()

    override fun initializeView(bind: FragmentMovieBinding) {
        val list = viewModel.getMovie()
        with(bind.rvMovieList){
            adapter = MovieAdapter(list)
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}