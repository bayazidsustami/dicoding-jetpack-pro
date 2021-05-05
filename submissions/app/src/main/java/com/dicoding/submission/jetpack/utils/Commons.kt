package com.dicoding.submission.jetpack.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object Commons {
    fun openBrowser(context: Context, url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }
}