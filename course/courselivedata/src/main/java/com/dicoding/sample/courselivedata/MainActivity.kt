package com.dicoding.sample.courselivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sample.courselivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mLiveDataTimerViewModel : MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        subscribe()
    }

    private fun subscribe(){
        val elapsedTimeObserver = Observer<Long?> { time ->
            val newText = resources.getString(R.string.seconds, time)
            binding.timerTextview.text = newText
        }

        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}