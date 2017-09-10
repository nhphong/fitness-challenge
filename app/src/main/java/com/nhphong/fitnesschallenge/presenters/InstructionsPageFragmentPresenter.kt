package com.nhphong.fitnesschallenge.presenters

import com.google.firebase.database.*
import com.nhphong.fitnesschallenge.Firebase
import com.nhphong.fitnesschallenge.database.keys.workouts
import com.nhphong.fitnesschallenge.fragments.InstructionsPageFragment
import com.google.firebase.database.DataSnapshot
import com.nhphong.fitnesschallenge.models.Workout


class InstructionsPageFragmentPresenter(fragment: InstructionsPageFragment?) : ValueEventListener {

    private var database: DatabaseReference? = Firebase.getInstance().database
    private var view: InstructionsPageFragment? = fragment

    fun syncData() {
        database?.child(workouts)?.addValueEventListener(this)
    }

    fun recycle() {
        database?.child(workouts)?.removeEventListener(this)
        database = null
        view = null
    }

    override fun onCancelled(error: DatabaseError?) {
        view?.displayError(error?.message!!)
    }

    override fun onDataChange(snapshot: DataSnapshot?) {
        val data = arrayListOf<Workout>()
        snapshot?.children?.forEach { workoutSnapshot ->
            val workout = workoutSnapshot.getValue(Workout::class.java)
            data.add(workout!!)
        }
        view?.refresh(data)
    }
}
