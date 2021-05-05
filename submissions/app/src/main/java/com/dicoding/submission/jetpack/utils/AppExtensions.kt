package com.dicoding.submission.jetpack.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewbinding.ViewBinding

inline fun <B: ViewBinding> ViewGroup.inflating(inflater: (LayoutInflater, ViewGroup, Boolean)-> B): B {
    return inflater(LayoutInflater.from(this.context), this, false)
}

fun Context.circularProgress(): CircularProgressDrawable{
    return CircularProgressDrawable(this).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
}