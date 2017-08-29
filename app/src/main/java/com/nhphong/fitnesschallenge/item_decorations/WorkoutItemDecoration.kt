package com.nhphong.fitnesschallenge.item_decorations

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.utils.getDimen

class WorkoutItemDecoration: RecyclerView.ItemDecoration() {
    private val space = getDimen(R.dimen.padding)

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        val position = parent?.getChildLayoutPosition(view)
        outRect?.top = if (position == 0 || position == 1) space else 0
        outRect?.left = if (position?.rem(2) == 0) space else space / 2
        outRect?.right = if (position?.rem(2) == 0) space / 2 else space
        outRect?.bottom = space
    }
}
