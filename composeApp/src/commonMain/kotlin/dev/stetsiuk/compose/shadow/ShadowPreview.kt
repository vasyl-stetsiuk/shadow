package dev.stetsiuk.compose.shadow

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

private val shape = RoundedCornerShape(24.dp)
private val size = 200.dp

@Preview
@Composable
fun ShadowColorPreview() {
    Box(
        modifier = Modifier
            .background(Color.Gray.copy(0.2f))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .shadow(
                    fillStyle = ShadowFillStyle.WithColor(Color.Black.copy(0.1f)),
                    shape = shape,
                    translationY = 16.dp,
                    translationX = 0.dp,
                    blurRadius = 24.dp,
                    spread = 4.dp
                )
                .background(Color.White, shape)
        )
    }
}

@Preview
@Composable
fun ShadowShaderPreview() {
    Box(
        modifier = Modifier
            .background(Color.Gray.copy(0.2f))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .shadow(
                    fillStyle = ShadowFillStyle.WithShader {
                        LinearGradientShader(
                            from = Offset(size.width / 2, 0f),
                            to = Offset(size.width / 2, size.height),
                            colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                        )
                    }, shape = shape,
                    translationY = 16.dp,
                    translationX = 0.dp,
                    blurRadius = 24.dp,
                    spread = 4.dp
                )
                .background(Color.White, shape)
        )
    }
}

@Preview
@Composable
fun ShadowBundlePreview() {
    Box(
        modifier = Modifier
            .background(Color.Gray.copy(0.2f))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .shadow(
                    bundle = ShadowBundle(
                        params = listOf(
                            ShadowParams(
                                fillStyle = ShadowFillStyle.WithShader {
                                    LinearGradientShader(
                                        from = Offset(size.width / 2, 0f),
                                        to = Offset(size.width / 2, size.height),
                                        colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                                    )
                                },
                                shape = shape,
                                translationY = 16.dp,
                                translationX = 0.dp,
                                blurRadius = 24.dp,
                                spread = 4.dp
                            ),
                            ShadowParams(
                                fillStyle = ShadowFillStyle.WithColor(
                                    Color.Black.copy(0.5f)
                                ),
                                shape = shape,
                                translationY = 16.dp,
                                translationX = 0.dp,
                                blurRadius = 24.dp,
                                spread = 4.dp
                            )
                        )
                    ),
                    defaultShape = shape
                )
                .background(Color.White, shape)
        )
    }
}

@Preview
@Composable
fun ShadowShader2Preview() {
    val fraction = remember { Animatable(0f) }
    val blurRadius = lerp(0.dp, 24.dp, fraction.value)
    val spread = lerp(0.dp, 4.dp, fraction.value)
    val translationY = lerp(0.dp, 16.dp, fraction.value)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
        if (isPressed) {
            fraction.animateTo(0f)
        } else {
            fraction.animateTo(1f, spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            ))
        }
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
                .size(size)
                .shadow(
                    fillStyle = ShadowFillStyle.WithShader {
                        LinearGradientShader(
                            from = Offset(size.width / 2, 0f),
                            to = Offset(size.width / 2, size.height),
                            colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                        )
                    },
                    shape = shape,
                    translationY = translationY,
                    blurRadius = blurRadius,
                    spread = spread
                )
                .background(Color.White, shape)
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