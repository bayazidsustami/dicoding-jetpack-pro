package com.dicoding.submission.jetpack.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide

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

fun ImageView.loadImage(context: Context, url: String){
    Glide.with(context)
        .load(url)
        .placeholder(context.circularProgress())
        .into(this)
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}