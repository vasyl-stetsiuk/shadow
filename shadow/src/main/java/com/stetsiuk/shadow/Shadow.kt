package com.stetsiuk.shadow

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.toArgb

fun Modifier.shadow(
    bundle: ShadowBundle,
    defaultShape: Shape = RectangleShape,
) = drawBehind {
    bundle.params.forEach { params ->
        drawShadow(
            fillStyle = params.fillStyle,
            shape = params.shape ?: defaultShape,
            blurRadius = params.blurRadius.toPx(),
            spread = params.spread.toPx(),
            translationX = params.translationX.toPx(),
            translationY = params.translationY.toPx(),
        )
    }
}

// Can default params be provided
fun Modifier.shadow(
    params: ShadowParams,
    defaultShape: Shape = RectangleShape,
) = drawBehind {
    drawShadow(
        fillStyle = params.fillStyle,
        shape = params.shape ?: defaultShape,
        blurRadius = params.blurRadius.toPx(),
        spread = params.spread.toPx(),
        translationX = params.translationX.toPx(),
        translationY = params.translationY.toPx(),
    )
}

fun DrawScope.drawShadow(
    fillStyle: FillStyle,
    shape: Shape,
    blurRadius: Float,
    spread: Float,
    translationX: Float = 0f,
    translationY: Float = 0f,
) {
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        val shadowSize = Size(
            width = size.width + (spread * 2),
            height = size.height + (spread * 2)
        )
        val scaleX = shadowSize.width / size.width
        val scaleY = shadowSize.height / size.height
        val outline = shape.createOutline(size, layoutDirection, this)

        frameworkPaint.apply {
            if (blurRadius > 0f) {
                maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
            }
            fillBackground(fillStyle)
        }

        withTransform(
            transformBlock = {
                translate(translationX, translationY)
            }
        ) {
            withTransform(
                transformBlock = {
                    scale(scaleX, scaleY)
                }
            ) {
                it.drawOutline(outline, paint)
            }
        }
    }
}

fun NativePaint.fillBackground(fillStyle: FillStyle) {
    when (fillStyle) {
        is FillStyle.WithColor -> {
            color = fillStyle.color.toArgb()
        }

        is FillStyle.WithShader -> {
            shader = fillStyle.shader
        }
    }
}

sealed interface FillStyle {
    data class WithColor(val color: Color) : FillStyle
    data class WithShader(val shader: Shader) : FillStyle
}