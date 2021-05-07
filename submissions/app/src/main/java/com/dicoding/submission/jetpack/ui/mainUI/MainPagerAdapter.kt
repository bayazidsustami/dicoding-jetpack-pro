package com.dicoding.submission.jetpack.ui.mainUI

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.submission.jetpack.ui.mainUI.fragments.movie.MovieFragment
import com.dicoding.submission.jetpack.ui.mainUI.fragments.tvShow.TvShowFragment

class MainPagerAdapter(fm: FragmentActivity): FragmentStateAdapter(fm) {

    companion object;

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> throw IllegalArgumentException("unexpected fragment")
        }
    }

}