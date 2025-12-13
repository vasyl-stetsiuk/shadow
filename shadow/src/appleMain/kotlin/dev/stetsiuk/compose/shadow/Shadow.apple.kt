package dev.stetsiuk.compose.shadow

import androidx.compose.ui.graphics.Paint
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter

internal actual fun Paint.applyBlur(blurRadius: Float) {
    val frameworkPaint = this.asFrameworkPaint()
    frameworkPaint.maskFilter = MaskFilter.makeBlur(FilterBlurMode.NORMAL, blurRadius)
}