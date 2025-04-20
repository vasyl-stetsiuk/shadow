package com.stetsiuk.shadow

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

data class ShadowBundle(
    val params: List<ShadowParams>
)

data class ShadowParams(
    val fillStyle: FillStyle = ShadowDefaults.fillStyle,
    val blurRadius: Dp = ShadowDefaults.blurRadius,
    val spread: Dp = ShadowDefaults.spread,
    val translationX: Dp = ShadowDefaults.translationX,
    val translationY: Dp = ShadowDefaults.translationY,
    val shape: Shape? = null // If is null then default value will be taken
)