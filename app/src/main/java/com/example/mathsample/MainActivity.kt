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
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathSampleTheme {
                RandomWalk()
            }
        }
    }
}

@Composable
fun RandomWalk() {
    val steps = remember { mutableStateListOf(Offset(0f, 0f)) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            repeat(100) { // 100ステップ移動
                delay(100) // 100msごとに更新
                val lastPos = steps.last()
                val nextPos = lastPos + randomStep()
                steps.add(nextPos)
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width / 2, size.height / 2)
        drawPoints(
            points = steps.map { center + it * 10f }, // 10倍拡大
            pointMode = PointMode.Polygon,
            color = Color.Cyan,
            strokeWidth = 3f
        )
    }
}

// ランダムな移動（上・下・左・右）
fun randomStep(): Offset {
    return when (Random.nextInt(4)) {
        0 -> Offset(10f, 0f)  // 右
        1 -> Offset(-10f, 0f) // 左
        2 -> Offset(0f, 10f)  // 下
        else -> Offset(0f, -10f) // 上
    }
}

@Preview
@Composable
fun PreviewRandomWalk() {
    RandomWalk()
}
