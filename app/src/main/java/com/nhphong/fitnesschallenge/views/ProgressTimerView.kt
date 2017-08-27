package com.nhphong.fitnesschallenge.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.nhphong.fitnesschallenge.databinding.ViewProgressTimerBinding
import com.nhphong.fitnesschallenge.view_models.ProgressTimerViewModel

class ProgressTimerView(context: Context, attrs: AttributeSet): FrameLayout(context, attrs) {

    private lateinit var binding: ViewProgressTimerBinding
    private lateinit var viewModel: ProgressTimerViewModel

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        val inflater = LayoutInflater.from(context)
        viewModel = ProgressTimerViewModel()
        binding = ViewProgressTimerBinding.inflate(inflater, this, true)
        binding.vm = viewModel

        //FIXME to be removed
        viewModel.startTimer()
    }

    override fun onDetachedFromWindow() {
        viewModel.recycle()
        super.onDetachedFromWindow()
    }
}
