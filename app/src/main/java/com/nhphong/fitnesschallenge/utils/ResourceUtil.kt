@file:JvmName("ResourceUtil")

package com.nhphong.fitnesschallenge.utils

import android.os.Build
import com.nhphong.fitnesschallenge.App

@Suppress("Deprecation")
fun getColor(id: Int): Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return App.instance.getColor(id)
    }
    return App.instance.resources.getColor(id)
}


fun getDimen(id: Int) = App.instance.resources.getDimensionPixelSize(id)
