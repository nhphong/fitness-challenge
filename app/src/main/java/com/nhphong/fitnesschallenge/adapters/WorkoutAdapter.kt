package com.nhphong.fitnesschallenge.adapters

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.activities.YoutubeActivity
import com.nhphong.fitnesschallenge.databinding.WorkoutItemBinding
import com.nhphong.fitnesschallenge.models.Workout
import com.nhphong.fitnesschallenge.modules.GlideApp
import com.nhphong.fitnesschallenge.presenters.WorkoutAdapterPresenter

class WorkoutAdapter(private val fragment: Fragment): RecyclerView.Adapter<WorkoutAdapter.Holder>() {

    private val presenter = WorkoutAdapterPresenter(this)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(fragment.context)
        val binding = WorkoutItemBinding.inflate(layoutInflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        presenter.bindViewHolder(holder, position)
    }

    override fun getItemCount(): Int = presenter.itemCount()

    fun refresh(workouts: ArrayList<Workout>) {
        presenter.refresh(workouts)
    }

    fun isActive() = fragment.isAdded

    fun loadImage(image: String, holder: Holder?) {
        GlideApp.with(fragment)
                .load(image)
                .placeholder(R.color.white)
                .into(holder?.binding?.ivWorkout)
    }

    fun loadText(text: String, holder: WorkoutAdapter.Holder?) {
        holder?.binding?.tvType?.text = text
    }

    fun setOnItemClickListener(video: String, holder: Holder?) {
        holder?.binding?.root?.setOnClickListener {
            YoutubeActivity.launch(fragment.context, video)
        }
    }

    fun removeOnItemClickListener(holder: Holder?) {
        holder?.binding?.root?.setOnClickListener(null)
    }

    class Holder(val binding: WorkoutItemBinding): RecyclerView.ViewHolder(binding.root)
}
