package com.stetsiuk.shadow

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp

private val Boolean.floatValue get() = if (this) 1f else 0f

@Preview
@Composable
fun BrushShadowPreview() {
    val shape = RoundedCornerShape(24.dp)
    val fraction = remember { Animatable(0f) }
    val blurRadius = lerp(0.dp, 24.dp, fraction.value)
    val spread = lerp(0.dp, 4.dp, fraction.value)
    val translationY = lerp(0.dp, 16.dp, fraction.value)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
        fraction.animateTo(1 - isPressed.floatValue)
    }

    Box(
        modifier = Modifier
            .background(Color.Gray.copy(0.2f))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    this.translationY = -translationY.toPx()
                }
                .size(200.dp)
                .shadowVerticalGradient(
                    colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                    shape = shape,
                    translationY = translationY,
                    blurRadius = blurRadius,
                    spread = spread
                )
                .clip(shape)
                .background(Color.White)
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) {},
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Press",
                color = Color.Black,
                fontSize = 32.sp
            )
        }
    }
}