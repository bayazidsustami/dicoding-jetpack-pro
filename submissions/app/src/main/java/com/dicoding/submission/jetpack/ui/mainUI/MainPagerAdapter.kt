package com.dicoding.submission.jetpack.ui.mainUI

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieFavoriteFragment
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieFragment
import com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow.TvShowFavoriteFragment
import com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow.TvShowFragment

class MainPagerAdapter(private val fm: FragmentActivity): FragmentStateAdapter(fm) {

    companion object;

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> if (fm is MainActivity) MovieFragment() else MovieFavoriteFragment()
            1 -> if (fm is MainActivity) TvShowFragment() else TvShowFavoriteFragment()
            else -> throw IllegalArgumentException("unexpected fragment")
        }
    }

}