package com.example.holyflix.utils

import android.view.View


val Boolean.Companion.False
    inline get() = false

val Boolean.Companion.True
    inline get() = true
fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

