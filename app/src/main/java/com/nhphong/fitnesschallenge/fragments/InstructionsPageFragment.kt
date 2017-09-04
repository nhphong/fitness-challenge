package com.nhphong.fitnesschallenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.utils.ResourceUtil

class InstructionsPageFragment: PageFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_page_instructions, container, false)
    }

    override fun getTitle(): String = ResourceUtil.getString(R.string.instructions)
}
