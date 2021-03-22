package com.valeriopapi.loadingbutton

import android.content.res.Resources
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

internal fun View.show() {
    this.visibility = View.VISIBLE
}

internal fun View.hide() {
    this.visibility = View.GONE

}

fun Drawable?.animateDrawable() {
    when (this) {
        is AnimatedVectorDrawableCompat -> {
            this.start()
        }
        is AnimatedVectorDrawable -> {
            this.start()
        }
    }
}

fun Int?.checkPositiveOrNull() = if (this != null && this >= 0) this else null