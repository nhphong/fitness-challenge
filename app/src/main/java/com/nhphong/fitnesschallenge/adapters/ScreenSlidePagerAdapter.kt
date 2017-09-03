package com.nhphong.fitnesschallenge.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.nhphong.fitnesschallenge.fragments.InstructionsPageFragment

class ScreenSlidePagerAdapter(fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    private val pages = arrayListOf(
            InstructionsPageFragment(),
            InstructionsPageFragment(),
            InstructionsPageFragment(),
            InstructionsPageFragment())

    override fun getItem(position: Int) = pages[position]

    override fun getCount() = pages.size
}
