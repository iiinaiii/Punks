package com.iiinaiii.punks.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.iiinaiii.punks.util.glide.GlideApp

@BindingAdapter("visibleUnless")
fun bindVisibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    url?.let {
        GlideApp.with(context).load(it).into(this)
    }
}