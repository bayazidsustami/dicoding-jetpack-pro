package com.dicoding.submission.jetpack.ui.mainUI.fragments.movie

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.data.movie.MoviesEntity
import com.dicoding.submission.jetpack.databinding.FragmentMovieBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment
import com.dicoding.submission.jetpack.ui.mainUI.OnItemClickListener
import com.dicoding.submission.jetpack.ui.mainUI.detail.movie.DetailMovieActivity
import com.dicoding.submission.jetpack.utils.Result
import com.dicoding.submission.jetpack.utils.gone
import com.dicoding.submission.jetpack.utils.visible

class MovieFragment: BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    private val viewModel: MovieViewModel by viewModels()

    override fun initializeView(bind: FragmentMovieBinding) {

        val movieAdapter = MovieAdapter()
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
                    movieAdapter.setData(dataList)
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