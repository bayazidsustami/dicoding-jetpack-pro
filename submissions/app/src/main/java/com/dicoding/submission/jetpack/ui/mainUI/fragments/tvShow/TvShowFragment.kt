package com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submission.jetpack.data.tvShows.TvShowsEntity
import com.dicoding.submission.jetpack.databinding.FragmentTvBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseFragment
import com.dicoding.submission.jetpack.ui.mainUI.OnItemClickListener
import com.dicoding.submission.jetpack.ui.mainUI.detail.tvShow.DetailTvShowActivity
import com.dicoding.submission.jetpack.utils.Result
import com.dicoding.submission.jetpack.utils.gone
import com.dicoding.submission.jetpack.utils.visible
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment: BaseFragment<FragmentTvBinding>(
    FragmentTvBinding::inflate
) {
    private val viewModel: TvShowViewModel by viewModel()
    private val tvAdapter: TvShowAdapter by inject()

    override fun initializeView(bind: FragmentTvBinding) {

        with(bind.rvTvShowList){
            adapter = tvAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.getListTvShows().observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    bind.progressBar.visible()
                }
                is Result.Error ->{
                    bind.progressBar.gone()
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    bind.progressBar.gone()
                    tvAdapter.setData(result.data)
                }
            }
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