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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.shadow(
    fillStyle: ShadowFillStyle = ShadowDefaults.fillStyle,
    blurRadius: Dp = ShadowDefaults.blurRadius,
    shape: Shape = RectangleShape,
    spread: Dp = ShadowDefaults.spread,
    translationX: Dp = 0.dp,
    translationY: Dp = 0.dp,
) = shadow(
    ShadowParams(fillStyle, blurRadius, spread, translationX, translationY, shape)
)

// Can default params be provided
fun Modifier.shadow(
    params: ShadowParams,
    defaultShape: Shape = RectangleShape,
) = shadow(
    bundle = ShadowBundle(listOf(params)),
    defaultShape = defaultShape
)

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

fun DrawScope.drawShadow(
    fillStyle: ShadowFillStyle,
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
            fillBackground(fillStyle, this@drawShadow)
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

private fun NativePaint.fillBackground(
    fillStyle: ShadowFillStyle,
    drawScope: DrawScope
) {
    when (fillStyle) {
        is ShadowFillStyle.WithColor -> {
            color = fillStyle.color.toArgb()
        }

        is ShadowFillStyle.WithShader -> {
            shader = fillStyle.shader.invoke(drawScope)
        }
    }
}

sealed interface ShadowFillStyle {
    data class WithColor(val color: Color) : ShadowFillStyle
    data class WithShader(
        val shader: DrawScope.() -> Shader
    ) : ShadowFillStyle
}