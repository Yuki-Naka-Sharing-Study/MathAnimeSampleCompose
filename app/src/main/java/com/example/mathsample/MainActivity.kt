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
            ParallelLines()
        }
    }
}

@Composable
fun ParallelLines() {
    var offset by remember { mutableStateOf(0f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("平行線")

        // 平行線の移動
        Slider(value = offset, onValueChange = { offset = it }, valueRange = -100f..100f, steps = 10, modifier = Modifier.fillMaxWidth())
        Text("オフセット: ${offset}")


        // グラフ描画
        Canvas(modifier = Modifier.fillMaxSize().height(300.dp)) {
            val width = size.width
            val height = size.height
            val centerY = height / 2

            // 平行線1
            drawLine(Color.Blue, Offset(0f, centerY + offset), Offset(width, centerY + offset), strokeWidth = 3f)

            // 平行線2
            drawLine(Color.Red, Offset(0f, centerY - offset), Offset(width, centerY - offset), strokeWidth = 3f)
        }
    }
}
