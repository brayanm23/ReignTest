package com.test.reign.extension

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AccelerateInterpolator

const val FADE_DURATION = 200L

fun View.fadeIn() {
    this.visibility = VISIBLE
    this.animate().alpha(1f).setDuration(FADE_DURATION)
        .setInterpolator(AccelerateInterpolator()).start()
}

fun View.fadeOut() {
    this.animate().alpha(0f).setDuration(FADE_DURATION)
        .setInterpolator(AccelerateInterpolator()).start()
    this.visibility = GONE
}
