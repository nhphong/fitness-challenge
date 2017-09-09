package com.nhphong.fitnesschallenge.utils

import android.content.Context
import android.widget.Toast

class ToastUtil private constructor() {
    companion object {
        private var toastMessage: Toast? = null

        @Synchronized fun showToast(message: String, context: Context) {
            toastMessage?.run {
                cancel()
            }

            toastMessage = Toast.makeText(context, message, Toast.LENGTH_LONG)
            toastMessage!!.show()
        }

        @Synchronized fun showToast(stringResId: Int, context: Context) {
            showToast(context.getString(stringResId), context)
        }
    }
}
