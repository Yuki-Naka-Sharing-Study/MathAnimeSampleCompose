package com.example.mathsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.tooling.preview.Preview
import com.example.mathsample.ui.theme.MathSampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathSampleTheme {
                EulerMethodSimulation()
            }
        }
    }
}

@Composable
fun EulerMethodSimulation() {
    val scope = rememberCoroutineScope()
    val points = remember { mutableStateListOf(Offset(0f, 0f)) }

    LaunchedEffect(Unit) {
        scope.launch {
            var x = 0f
            var y = 1f  // 初期値 y(0) = 1
            val h = 0.1f  // 刻み幅
            repeat(100) {  // 100ステップ計算
                delay(100)
                val dydx = -y  // 例: dy/dx = -y (指数関数的減衰)
                y += h * dydx
                x += h
                points.add(Offset(x * 100, y * 100))  // スケール調整
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width / 4, size.height / 2)
        drawPoints(
            points = points.map { center + it },
            pointMode = PointMode.Polygon,
            color = Color.Cyan,
            strokeWidth = 3f
        )
    }
}

@Preview
@Composable
fun PreviewEulerMethod() {
    EulerMethodSimulation()
}
