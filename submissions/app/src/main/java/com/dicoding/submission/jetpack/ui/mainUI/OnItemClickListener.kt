package com.dicoding.submission.jetpack.ui.mainUI

interface OnItemClickListener<in T> {
    fun onClick(data: T)
}