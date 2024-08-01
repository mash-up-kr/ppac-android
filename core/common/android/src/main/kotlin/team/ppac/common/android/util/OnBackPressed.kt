package team.ppac.common.android.util

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback

fun ComponentActivity.onBackPressed(action: () -> Unit) {
    val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            action()
        }
    }
    onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
}