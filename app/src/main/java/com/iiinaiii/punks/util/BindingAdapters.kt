package com.iiinaiii.punks.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleUnless")
fun bindVisibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}