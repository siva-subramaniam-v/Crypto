package com.example.crypto.util

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.crypto.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Log.i("BindingAdapter", imgUrl?:"empty image url")
    imgUrl?.let {
        Log.i("BindingAdapter", imgUrl)
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide
            .with(imgView.context)
            .load(imgUri)
            .centerCrop()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}