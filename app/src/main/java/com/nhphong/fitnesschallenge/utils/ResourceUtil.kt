package com.nhphong.fitnesschallenge.utils

import android.os.Build
import com.nhphong.fitnesschallenge.App

class ResourceUtil {
    companion object {
        @Suppress("Deprecation")
        fun getColor(id: Int): Int {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return App.instance.getColor(id)
            }
            return App.instance.resources.getColor(id)
        }


        fun getDimen(id: Int) = App.instance.resources.getDimensionPixelSize(id)

        fun getString(id: Int) = App.instance.resources.getString(id)
    }
}
