package com.dicoding.submission.jetpack.ui.mainUI

import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.jetpack.R
import com.dicoding.submission.jetpack.databinding.ActivityMainBinding
import com.dicoding.submission.jetpack.ui.baseUI.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity<ActivityMainBinding>(
        ActivityMainBinding::inflate
){
    override fun initializeView(bind: ActivityMainBinding) {
        supportActionBar?.run {
            elevation = 0f
            title = resources.getString(R.string.app_name)
        }
        with(bind.viewPager){
            adapter = MainPagerAdapter(this@MainActivity)
            (this.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        TabLayoutMediator(bind.tabView, bind.viewPager){ tab, position ->
            when(position){
                0 -> tab.text = resources.getString(R.string.movie)
                1 -> tab.text = resources.getString(R.string.tvShow)
            }
        }.attach()
    }
}