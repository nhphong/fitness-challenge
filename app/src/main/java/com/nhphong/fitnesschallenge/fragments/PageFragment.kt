package com.nhphong.fitnesschallenge.fragments

import android.support.v4.app.Fragment

abstract class PageFragment: Fragment() {
    abstract fun getTitle(): String
}
