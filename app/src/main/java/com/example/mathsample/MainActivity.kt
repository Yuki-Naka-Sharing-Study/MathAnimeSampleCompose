package com.example.mathsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LineGraph()
        }
    }
}

@Composable
fun LineGraph() {
    var slope by remember { mutableFloatStateOf(1f) }
    var intercept by remember { mutableFloatStateOf(0f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("直線の方程式: y = mx + b")

        Slider(
            value = slope,
            onValueChange = { slope = it },
            valueRange = -5f..5f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("傾き (m) = $slope")

        Slider(
            value = intercept,
            onValueChange = { intercept = it },
            valueRange = -5f..5f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("切片 (b) = $intercept")

        Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            val width = size.width
            val height = size.height
            val scale = 50f // 1単位を50pxにスケール

            for (x in 0 until width.toInt()) {
                val realX = (x - width / 2) / scale
                val y = slope * realX + intercept
                val screenY = height / 2 - y * scale
                drawCircle(
                    color = Color.Blue,
                    radius = 2f,
                    center = Offset(x.toFloat(), screenY)
                )
            }
        }
    }
}
