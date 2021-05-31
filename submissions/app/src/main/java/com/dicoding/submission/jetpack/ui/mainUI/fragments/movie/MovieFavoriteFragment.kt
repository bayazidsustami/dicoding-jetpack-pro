package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.databinding.FragmentMovieBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment
import com.dicoding.submission.jetpack.ui.mainUI.detail.movie.DetailMovieActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment: BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    private val viewModel: MovieFavoriteViewModel by viewModel()
    private val adapterMovie: MovieAdapter by inject()

    override fun initializeView(bind: FragmentMovieBinding) {

        with(bind.rvMovieList){
            adapter = adapterMovie
            layoutManager = GridLayoutManager(requireContext(), 2)
            hasFixedSize()
        }

        viewModel.getMovie().observe(viewLifecycleOwner){data ->
            adapterMovie.submitList(data)
        }

        adapterMovie.onItemSelected = {data ->
            val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
                putExtra(DetailMovieActivity.EXTRA_DATA_MOVIE, data)
            }
            startActivity(intent)
        }
    }
}