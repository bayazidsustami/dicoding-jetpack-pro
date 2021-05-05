package com.dicoding.submission.jetpack.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <B: ViewBinding> ViewGroup.inflating(inflater: (LayoutInflater, ViewGroup, Boolean)-> B): B {
    return inflater(LayoutInflater.from(this.context), this, false)
}