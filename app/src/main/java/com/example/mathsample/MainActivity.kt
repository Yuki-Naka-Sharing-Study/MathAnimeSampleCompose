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
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SineWaveGraph()
        }
    }
}

@Composable
fun SineWaveGraph() {
    var frequency by remember { mutableFloatStateOf(1f) }
    var amplitude by remember { mutableFloatStateOf(1f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("サイン波: y = A * sin(ωt)")

        Slider(
            value = frequency,
            onValueChange = { frequency = it },
            valueRange = 0.5f..5f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("周波数 (f) = $frequency")

        Slider(
            value = amplitude,
            onValueChange = { amplitude = it },
            valueRange = 0.5f..5f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("振幅 (A) = $amplitude")

        Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            val width = size.width
            val height = size.height
            val scale = 100f // 1単位が100px

            for (x in 0 until width.toInt()) {
                val t = x / width.toFloat() * 2 * Math.PI.toFloat()
                val y = amplitude * sin(frequency * t)
                val screenY = height / 2 - y * scale
                drawCircle(
                    color = Color.Green,
                    radius = 2f,
                    center = Offset(x.toFloat(), screenY)
                )
            }
        }
    }
}
