package com.stetsiuk.shadow

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.shadowVerticalGradient(
    colors: List<Color>,
    blurRadius: Dp = ShadowDefaults.blurRadius,
    shape: Shape = RectangleShape,
    spread: Dp = ShadowDefaults.spread,
    translationX: Dp = 0.dp,
    translationY: Dp = 0.dp,
    onShader: DrawScope.() -> Shader = {
        LinearGradientShader(
            from = Offset(size.width / 2, 0f),
            to = Offset(size.width / 2, size.height),
            colors = colors,
        )
    }
) = drawBehind {
    val shader = onShader()
    drawShadow(
        fillStyle = FillStyle.WithShader(shader),
        shape = shape,
        blurRadius = blurRadius.toPx(),
        translationX = translationX.toPx(),
        translationY = translationY.toPx(),
        spread = spread.toPx(),
    )
}