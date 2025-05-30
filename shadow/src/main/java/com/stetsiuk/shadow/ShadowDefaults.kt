package com.stetsiuk.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ShadowDefaults {
    val fillStyle: ShadowFillStyle = ShadowFillStyle.WithColor(
        Color.Black.copy(0.10f)
    )
    val blurRadius: Dp = 24.dp
    val spread: Dp = 4.dp
    val translationX: Dp = 0.dp
    val translationY: Dp = 0.dp
}