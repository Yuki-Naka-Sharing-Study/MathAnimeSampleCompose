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
            ParabolaGraph()
        }
    }
}

@Composable
fun ParabolaGraph() {
    var a by remember { mutableStateOf(1f) }
    var b by remember { mutableStateOf(0f) }
    var c by remember { mutableStateOf(0f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("放物線: y = ax² + bx + c")

        Slider(
            value = a,
            onValueChange = { a = it },
            valueRange = -5f..5f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("a = $a")

        Slider(
            value = b,
            onValueChange = { b = it },
            valueRange = -5f..5f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("b = $b")

        Slider(
            value = c,
            onValueChange = { c = it },
            valueRange = -5f..5f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("c = $c")

        Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            val width = size.width
            val height = size.height
            val scale = 50f // 1単位が50px
            for (x in 0 until width.toInt()) {
                val realX = (x - width / 2) / scale
                val y = a * realX * realX + b * realX + c
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
