package com.example.mathsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathSampleTheme {
                NewtonMethodAnimation()
            }
        }
    }
}

@Composable
fun NewtonMethodAnimation() {
    val scope = rememberCoroutineScope()
    val xValue = remember { Animatable(2f) } // 初期値
    val function = { x: Float -> x * x - 2 } // 例: f(x) = x^2 - 2（√2 を求める）
    val derivative = { x: Float -> 2 * x }   // 例: f'(x) = 2x
    val canvasSize = 400.dp

    LaunchedEffect(Unit) {
        scope.launch {
            repeat(5) { // 5回の反復
                val xNew = xValue.value - function(xValue.value) / derivative(xValue.value)
                xValue.animateTo(xNew, animationSpec = tween(1000))
            }
        }
    }

    Canvas(modifier = Modifier.size(canvasSize)) {
        val width = size.width
        val height = size.height
        val scale = width / 4f

        fun toScreen(x: Float, y: Float): Offset {
            return Offset(width / 2 + x * scale, height / 2 - y * scale)
        }

        // 関数のグラフ
        val path = Path().apply {
            for (i in -200..200) {
                val x = i / 50f
                val y = function(x)
                if (i == -200) moveTo(toScreen(x, y).x, toScreen(x, y).y)
                else lineTo(toScreen(x, y).x, toScreen(x, y).y)
            }
        }
        drawPath(path, Color.Blue, style = Stroke(2f))

        // 現在の x の値
        val x = xValue.value
        val y = function(x)
        val slope = derivative(x)
        val intercept = y - slope * x

        // 接線
        val tangentPath = Path().apply {
            for (i in -200..200) {
                val tx = i / 50f
                val ty = slope * tx + intercept
                if (i == -200) moveTo(toScreen(tx, ty).x, toScreen(tx, ty).y)
                else lineTo(toScreen(tx, ty).x, toScreen(tx, ty).y)
            }
        }
        drawPath(tangentPath, Color.Red, style = Stroke(2f))

        // 点の描画
        drawCircle(Color.Red, 6f, toScreen(x, y))
    }
}

@Preview
@Composable
fun PreviewNewtonMethod() {
    NewtonMethodAnimation()
}
