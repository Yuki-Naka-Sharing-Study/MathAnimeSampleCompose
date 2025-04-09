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
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AreaOfRectangle()
        }
    }
}

@Composable
fun AreaOfRectangle() {
    var width by remember { mutableFloatStateOf(5f) }
    var height by remember { mutableFloatStateOf(3f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("長方形の面積: 面積 = 幅 × 高さ")

        Slider(value = width, onValueChange = { width = it }, valueRange = 1f..10f, steps = 10, modifier = Modifier.fillMaxWidth())
        Text("幅: $width")

        Slider(value = height, onValueChange = { height = it }, valueRange = 1f..10f, steps = 10, modifier = Modifier.fillMaxWidth())
        Text("高さ: $height")

        Text("面積: ${width * height}")

        Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
            val widthPx = width * 30f
            val heightPx = height * 30f

            drawRect(color = Color.Green, size = androidx.compose.ui.geometry.Size(widthPx, heightPx))
        }
    }
}
