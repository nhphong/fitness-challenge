package com.nhphong.fitnesschallenge.presenters

import android.text.TextUtils
import com.nhphong.fitnesschallenge.adapters.WorkoutAdapter
import com.nhphong.fitnesschallenge.models.Workout

class WorkoutAdapterPresenter(private val view: WorkoutAdapter) {
    private val data = arrayListOf<Workout>()

    fun itemCount() = data.size

    fun refresh(newData: ArrayList<Workout>?) {
        data.clear()
        newData?.apply {
            data.addAll(this)
        }
        view.notifyDataSetChanged()
    }

    fun bindViewHolder(holder: WorkoutAdapter.Holder?, position: Int) {
        val item = data[position]
        if (!TextUtils.isEmpty(item.image) && view.isActive()) {
            view.loadImage(item.image, holder)
        }
        view.loadText(item.type, holder)
        if (!TextUtils.isEmpty(item.video)) {
            view.setOnItemClickListener(item.video, holder)
        } else {
            view.removeOnItemClickListener(holder)
        }
    }
}
