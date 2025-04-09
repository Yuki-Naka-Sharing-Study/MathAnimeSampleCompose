package com.example.mathsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import com.example.mathsample.ui.theme.MathSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathSampleTheme {
                DifferentiationAnimation()
            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun DifferentiationAnimation() {
    val progress = remember { Animatable(0f) }

    // アニメーションを制御
    LaunchedEffect(Unit) {
        progress.animateTo(1f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ))
    }

    MotionLayout(
        start = ConstraintSet {
            val point = createRefFor("point")
            constrain(point) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        },
        end = ConstraintSet {
            val point = createRefFor("point")
            constrain(point) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        },
        progress = progress.value,
        modifier = Modifier.fillMaxSize()
    ) {
        // グラフを描画
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val centerX = width / 2
            val centerY = height / 2
            val scale = width / 10

            drawLine(
                color = Color.Gray,
                start = Offset(0f, centerY),
                end = Offset(width, centerY),
                strokeWidth = 2f
            )

            drawLine(
                color = Color.Gray,
                start = Offset(centerX, 0f),
                end = Offset(centerX, height),
                strokeWidth = 2f
            )

            for (x in -5..5) {
                val x1 = x.toFloat()
                val y1 = x1 * x1
                val x2 = (x1 + 0.1f)
                val y2 = x2 * x2

                drawLine(
                    color = Color.Blue,
                    start = Offset(centerX + x1 * scale, centerY - y1 * scale),
                    end = Offset(centerX + x2 * scale, centerY - y2 * scale),
                    strokeWidth = 4f
                )
            }
        }

        // 接線の動き
        val x = progress.value * 5f - 2.5f
        val y = x * x
        val slope = 2 * x
        val tangentStartX = x - 1f
        val tangentEndX = x + 1f
        val tangentStartY = y + slope * (tangentStartX - x)
        val tangentEndY = y + slope * (tangentEndX - x)

        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val centerX = width / 2
            val centerY = height / 2
            val scale = width / 10

            drawLine(
                color = Color.Red,
                start = Offset(centerX + tangentStartX * scale, centerY - tangentStartY * scale),
                end = Offset(centerX + tangentEndX * scale, centerY - tangentEndY * scale),
                strokeWidth = 4f
            )

            drawCircle(
                color = Color.Red,
                center = Offset(centerX + x * scale, centerY - y * scale),
                radius = 10f
            )
        }
    }
}
