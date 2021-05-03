package com.dicoding.submission.jetpack.ui.baseUI

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B: ViewBinding>(val bindingFactory: (LayoutInflater) -> B): AppCompatActivity() {
    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: B
        get() = _binding as B

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding = bindingFactory(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        initializeView(binding)
    }

    abstract fun initializeView(bind: B)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}