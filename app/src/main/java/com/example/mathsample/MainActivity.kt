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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mathsample.ui.theme.MathSampleTheme
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathSampleTheme {
                VectorRotationAnimation()
            }
        }
    }
}

@Composable
fun VectorRotationAnimation() {
    val angle = remember { Animatable(0f) }

    // アニメーションを設定（角度が360°回転）
    LaunchedEffect(Unit) {
        angle.animateTo(
            360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 5000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(300.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val length = size.width / 3
            val radians = Math.toRadians(angle.value.toDouble())

            // 回転後の座標を計算
            val x = (length * cos(radians)).toFloat()
            val y = (length * sin(radians)).toFloat()

            // 軸
            drawLine(Color.Gray, Offset(center.x, 0f), Offset(center.x, size.height), strokeWidth = 2f)
            drawLine(Color.Gray, Offset(0f, center.y), Offset(size.width, center.y), strokeWidth = 2f)

            // ベクトル
            drawLine(Color.Blue, center, Offset(center.x + x, center.y - y), strokeWidth = 6f)

            // ベクトルの先端
            drawCircle(Color.Red, radius = 10f, center = Offset(center.x + x, center.y - y))
        }
    }
}
