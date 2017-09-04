package com.nhphong.fitnesschallenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.databinding.FragmentPageMyPlanBinding
import com.nhphong.fitnesschallenge.utils.ResourceUtil

class MyPlanPageFragment: PageFragment() {
    private lateinit var binding: FragmentPageMyPlanBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPageMyPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTitle(): String = ResourceUtil.getString(R.string.my_plan)

    override fun getIcon(): Int = android.R.drawable.ic_btn_speak_now
}
