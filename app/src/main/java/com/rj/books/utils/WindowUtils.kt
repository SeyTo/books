package com.rj.books.utils

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat

fun colorizeStatusBar(activity: Activity, color: Int) {
    val window: Window = activity.window
    // clear FLAG_TRANSLUCENT_STATUS flag:
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    // finally change the color
    window.statusBarColor = color
}