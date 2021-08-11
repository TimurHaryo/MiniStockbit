package id.timtam.core.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.snack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).also { snackbar ->
        snackbar.setAction("OK") { snackbar.dismiss() }
    }.show()
}