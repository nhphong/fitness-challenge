package com.nhphong.fitnesschallenge.fragments

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.adapters.WorkoutAdapter
import com.nhphong.fitnesschallenge.databinding.FragmentPageInstructionsBinding
import com.nhphong.fitnesschallenge.item_decorations.WorkoutItemDecoration
import com.nhphong.fitnesschallenge.utils.ResourceUtil

class InstructionsPageFragment: PageFragment() {
    private lateinit var binding: FragmentPageInstructionsBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPageInstructionsBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = WorkoutAdapter(this)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.addItemDecoration(WorkoutItemDecoration())
        return binding.root
    }

    override fun getTitle(): String = ResourceUtil.getString(R.string.instructions)

    override fun getIcon(): Int = android.R.drawable.ic_btn_speak_now
}
