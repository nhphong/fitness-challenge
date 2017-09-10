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
import com.nhphong.fitnesschallenge.models.Workout
import com.nhphong.fitnesschallenge.presenters.InstructionsPageFragmentPresenter
import com.nhphong.fitnesschallenge.utils.ResourceUtil
import com.nhphong.fitnesschallenge.utils.ToastUtil

class InstructionsPageFragment: PageFragment() {

    private lateinit var presenter: InstructionsPageFragmentPresenter
    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPageInstructionsBinding.inflate(inflater, container, false)
        adapter = WorkoutAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.addItemDecoration(WorkoutItemDecoration())

        presenter = InstructionsPageFragmentPresenter(this)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.syncData()
    }

    override fun onDestroy() {
        presenter.recycle()
        super.onDestroy()
    }

    override fun getTitle(): String = ResourceUtil.getString(R.string.instructions)

    override fun getIcon(): Int = android.R.drawable.ic_btn_speak_now

    fun displayError(message: String) {
        ToastUtil.showToast(message, context)
    }

    fun refresh(workouts: ArrayList<Workout>) {
        adapter.refresh(workouts)
    }
}
