package com.nhphong.fitnesschallenge.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.databinding.WorkoutItemBinding
import com.nhphong.fitnesschallenge.models.Workout

class WorkoutAdapter(val activity: Activity): RecyclerView.Adapter<WorkoutAdapter.Holder>() {

    private val data = arrayListOf<Workout>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(activity)
        val binding = WorkoutItemBinding.inflate(layoutInflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val item = data[position]
        if (!TextUtils.isEmpty(item.imageUrl) && !activity.isFinishing) {
            Glide.with(activity)
                    .load(item.imageUrl)
                    .apply(RequestOptions().placeholder(R.color.white))
                    .into(holder?.binding?.ivWorkout)
        }
        holder?.binding?.tvType?.text = item.type.toString()
    }

    override fun getItemCount(): Int = data.size

    fun refresh(data: ArrayList<Workout>?) {
        this.data.clear()
        data?.apply {
            this@WorkoutAdapter.data.addAll(this)
        }
    }

    class Holder(val binding: WorkoutItemBinding): RecyclerView.ViewHolder(binding.root)
}
