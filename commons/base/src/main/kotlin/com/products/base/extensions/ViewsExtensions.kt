package com.products.base.extensions

import android.view.View

/**
 * Extension function that help to show a determinate view.
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Extension function that help to show a determinate view.
 * @param gone false if the view should be invisible, true otherwise.
 */
fun View.hide(gone: Boolean = true) {
    visibility = if (gone) {
        View.GONE
    } else {
        View.INVISIBLE
    }
}
