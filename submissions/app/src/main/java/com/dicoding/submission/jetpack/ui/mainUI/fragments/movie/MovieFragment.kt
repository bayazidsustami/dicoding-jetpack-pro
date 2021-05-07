package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.databinding.FragmentMovieBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment
import com.dicoding.submission.jetpack.ui.mainUI.OnItemClickListener
import com.dicoding.submission.jetpack.ui.mainUI.detail.movie.DetailMovieActivity

class MovieFragment: BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {
    private val viewModel: MovieViewModel by viewModels()

    override fun initializeView(bind: FragmentMovieBinding) {
        val list = viewModel.getMovie()
        val movieAdapter = MovieAdapter(list)
        with(bind.rvMovieList){
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        movieAdapter.setOnItemCLickListener(object : OnItemClickListener<MoviesEntity>{
            override fun onClick(data: MoviesEntity) {
                val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
                    putExtra(DetailMovieActivity.EXTRA_DATA_MOVIE, data)
                }
                startActivity(intent)
            }
        })
    }
}