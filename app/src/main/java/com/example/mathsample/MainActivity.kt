package com.example.mathsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mathsample.ui.theme.MathSampleTheme
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathSampleTheme {
                ParametricCurve()
            }
        }
    }
}

@Composable
fun ParametricCurve() {
    val tValue = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val canvasSize = 400.dp

    LaunchedEffect(Unit) {
        scope.launch {
            tValue.animateTo(
                10f,
                animationSpec = tween(
                    5000,
                    easing = LinearEasing
                )
            )
        }
    }

    Canvas(modifier = Modifier.size(canvasSize)) {
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2
        val scale = width / 4f

        val path = Path().apply {
            for (i in 0 until 1000) {
                val t = (i / 1000f) * tValue.value
                val x = sin(3 * t) * cos(t)
                val y = sin(2 * t)
                val point = Offset(centerX + x * scale, centerY - y * scale)
                if (i == 0) moveTo(point.x, point.y) else lineTo(point.x, point.y)
            }
        }
        drawPath(path, Color.Cyan, style = Stroke(3f))
    }
}

@Preview
@Composable
fun PreviewParametricCurve() {
    ParametricCurve()
}
