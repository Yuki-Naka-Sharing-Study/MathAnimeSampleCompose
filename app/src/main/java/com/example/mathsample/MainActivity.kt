package com.example.mathsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegularPolygon()
        }
    }
}

@Composable
fun RegularPolygon() {
    var sides by remember { mutableIntStateOf(3) }

    // 正多角形の内角の計算
    val interiorAngle = (sides - 2) * 180f / sides

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("正多角形の内角")

        // 辺の数のスライダー
        Slider(value = sides.toFloat(), onValueChange = { sides = it.toInt() }, valueRange = 3f..10f, steps = 7, modifier = Modifier.fillMaxWidth())
        Text("辺の数: $sides")

        // 内角の表示
        Text("内角: $interiorAngle°")

        // 正多角形の描画
        Canvas(modifier = Modifier.fillMaxSize().height(300.dp)) {
            val width = size.width
            val height = size.height
            val centerX = width / 2
            val centerY = height / 2
            val radius = 100f
            val angleStep = 360f / sides
            val path = Path().apply {
                moveTo(centerX + radius, centerY)
                for (i in 1 until sides) {
                    val angle = Math.toRadians(angleStep * i.toDouble()).toFloat()
                    lineTo(centerX + radius * cos(angle.toDouble()).toFloat(), centerY + radius * sin(
                        angle.toDouble()
                    ).toFloat())
                }
                close()
            }
            drawPath(path, color = Color.Blue, style = Stroke(width = 2f))
        }
    }
}
