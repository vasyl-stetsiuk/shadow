package com.stetsiuk.shadow

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.shadow(
    blurRadius: Dp = ShadowDefaults.blurRadius,
    color: Color = ShadowDefaults.color,
    shape: Shape = RectangleShape,
    spread: Dp = ShadowDefaults.spread,
    translationX: Dp = 0.dp,
    translationY: Dp = 0.dp,
) = drawBehind {
    drawShadow(
        fillStyle = FillStyle.WithColor(color),
        shape = shape,
        blurRadius = blurRadius.toPx(),
        translationX = translationX.toPx(),
        translationY = translationY.toPx(),
        spread = spread.toPx(),
    )
}