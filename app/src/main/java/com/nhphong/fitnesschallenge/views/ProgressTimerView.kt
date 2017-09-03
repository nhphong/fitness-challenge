package com.nhphong.fitnesschallenge.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.nhphong.fitnesschallenge.R
import com.nhphong.fitnesschallenge.databinding.ViewProgressTimerBinding
import com.nhphong.fitnesschallenge.utils.getColor
import com.nhphong.fitnesschallenge.utils.getDimen
import com.nhphong.fitnesschallenge.view_models.ProgressTimerViewModel
import io.reactivex.disposables.CompositeDisposable

class ProgressTimerView(context: Context, attrs: AttributeSet): FrameLayout(context, attrs) {
    private val binding = ViewProgressTimerBinding.inflate(LayoutInflater.from(context), this, true)
    private val viewModel = ProgressTimerViewModel()
    private var disposable = CompositeDisposable()
    private val boundingRect = RectF()
    private val radius = getDimen(R.dimen.corner_radius).toFloat()
    private val sHalfWidth = getDimen(R.dimen.stroke_width) / 2f
    private var currentProgress = 0f
    private val paint = Paint().apply {
        strokeWidth = sHalfWidth * 2
        isAntiAlias = true
    }

    init {
        binding.vm = viewModel
        disposable.add(viewModel.progressObs.subscribe {
            currentProgress = it
            //TODO Only invalidate portion of the view
            invalidate()
        })
    }

    fun startTimer() {
        viewModel.startTimer()
    }

    fun stopTimer() {
        viewModel.stopTimer()
    }

    override fun onDetachedFromWindow() {
        viewModel.recycle()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onDetachedFromWindow()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(boundingRect.apply {
            left = sHalfWidth
            top = sHalfWidth
            right = canvas.width - sHalfWidth
            bottom = canvas.height - sHalfWidth
        }, radius, radius, paint.apply {
            style = Paint.Style.FILL
            color = getColor(R.color.gray)
        })

        if (currentProgress > 0f) {
            canvas?.drawRoundRect(boundingRect.apply {
                right = left + currentProgress * (canvas.width - 2 * sHalfWidth)
            }, radius, radius, paint.apply {
                color = getColor(R.color.green_light)
            })

            if (currentProgress < 1f) {
                canvas?.drawRect(boundingRect.apply { left += radius }, paint)
            }
        }

        canvas?.drawRoundRect(boundingRect.apply {
            left = sHalfWidth
            top = sHalfWidth
            right = canvas.width - sHalfWidth
            bottom = canvas.height - sHalfWidth
        }, radius, radius, paint.apply {
            style = Paint.Style.STROKE
            color = getColor(R.color.light_gray)
        })

        super.dispatchDraw(canvas)
    }
}
