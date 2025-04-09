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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.mathsample.ui.theme.MathSampleTheme
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathSampleTheme {
                FourierWaveAnimation()
            }
        }
    }
}

@Composable
fun FourierWaveAnimation() {
    val time = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        time.animateTo(
            10f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 5000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val centerY = height / 2

        // 波の描画
        val path = Path()
        for (i in 0..width.toInt()) {
            val x = i.toFloat()
            val t = time.value
            val y = sin((x / width) * 2 * Math.PI + t).toFloat() +
                    (1 / 3f) * sin((3 * x / width) * 2 * Math.PI + t).toFloat() +
                    (1 / 5f) * sin((5 * x / width) * 2 * Math.PI + t).toFloat()

            if (i == 0) {
                path.moveTo(x, centerY - y * 100)
            } else {
                path.lineTo(x, centerY - y * 100)
            }
        }
        drawPath(path, Color.Blue, style = Stroke(width = 4f))
    }
}
