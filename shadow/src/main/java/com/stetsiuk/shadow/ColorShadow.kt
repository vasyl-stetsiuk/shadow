package com.stetsiuk.shadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.shadow(
    color: Color,
    blurRadius: Dp = ShadowDefaults.blurRadius,
    shape: Shape = RectangleShape,
    spread: Dp = ShadowDefaults.spread,
    translationX: Dp = 0.dp,
    translationY: Dp = 0.dp,
) = shadow(
    fillStyle = ShadowFillStyle.WithColor(color),
    blurRadius = blurRadius,
    shape = shape,
    spread = spread,
    translationX = translationX,
    translationY = translationY
)