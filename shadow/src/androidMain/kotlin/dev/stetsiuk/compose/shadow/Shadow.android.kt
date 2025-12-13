package dev.stetsiuk.compose.shadow

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.Paint

internal actual fun Paint.applyBlur(blurRadius: Float) {
    val frameworkPaint = this.asFrameworkPaint()
    frameworkPaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
}