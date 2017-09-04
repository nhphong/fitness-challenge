package com.nhphong.fitnesschallenge.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.nhphong.fitnesschallenge.fragments.InstructionsPageFragment
import com.nhphong.fitnesschallenge.fragments.MyPlanPageFragment

class ScreenSlidePagerAdapter(fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    private val pages = arrayListOf(
            InstructionsPageFragment(),
            MyPlanPageFragment(),
            InstructionsPageFragment(),
            MyPlanPageFragment())

    override fun getItem(position: Int) = pages[position]

    override fun getCount() = pages.size

    override fun getPageTitle(position: Int) = pages[position].getTitle()

    fun getPageIcon(position: Int) = pages[position].getIcon()
}
