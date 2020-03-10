package com.app.geojeff.utils

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.SeekBar

class SeekBarAnimation(
    private val seekBar: SeekBar,
    private val from: Float,
    private val to: Float
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        seekBar.progress = value.toInt()
    }

}