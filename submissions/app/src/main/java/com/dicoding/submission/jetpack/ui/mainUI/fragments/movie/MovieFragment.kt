package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.databinding.FragmentMovieBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment
import com.dicoding.submission.jetpack.ui.mainUI.OnItemClickListener
import com.dicoding.submission.jetpack.ui.mainUI.detail.movie.DetailMovieActivity
import com.dicoding.submission.jetpack.utils.Result
import com.dicoding.submission.jetpack.utils.gone
import com.dicoding.submission.jetpack.utils.visible
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment: BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    private val viewModel: MovieViewModel by viewModel()
    private val movieAdapter: MovieAdapter by inject()

    override fun initializeView(bind: FragmentMovieBinding) {

        with(bind.rvMovieList){
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.getMovie().observe(viewLifecycleOwner){ result->
            when(result){
                is Result.Loading -> {
                    bind.progressBar.visible()
                }
                is Result.Error -> {
                    bind.progressBar.gone()
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    bind.progressBar.gone()
                    val dataList = result.data
                    movieAdapter.submitList(dataList)
                }
            }
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