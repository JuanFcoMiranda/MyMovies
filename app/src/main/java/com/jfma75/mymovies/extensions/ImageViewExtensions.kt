package com.jfma75.mymovies.extensions

import android.widget.ImageView
import coil.Coil
import coil.ImageLoader
import coil.api.load
import coil.request.LoadRequestBuilder
import coil.request.RequestDisposable

//import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) {
  //  Glide.with(context).load(url).into(this)
}

inline fun ImageView.load(url: String?, imageLoader: ImageLoader = Coil.loader(), builder: LoadRequestBuilder.() -> Unit = {}): RequestDisposable {
    return imageLoader.load(context, url) {
        target(this@load)
        builder()
    }
}