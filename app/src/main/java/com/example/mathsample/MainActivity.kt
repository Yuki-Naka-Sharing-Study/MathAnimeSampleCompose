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
            InverseGraph()
        }
    }
}

@Composable
fun InverseGraph() {
    var k by remember { mutableStateOf(1f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("反比例の方程式: y = k/x")

        Slider(
            value = k,
            onValueChange = { k = it },
            valueRange = 0f..10f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("定数 k = $k")

        Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            val width = size.width
            val height = size.height
            val scale = 50f // 1単位を50pxにスケール

            for (x in 1 until width.toInt()) {
                val realX = (x - width / 2) / scale
                val y = k / realX
                val screenY = height / 2 - y * scale
                drawCircle(
                    color = Color.Cyan,
                    radius = 2f,
                    center = Offset(x.toFloat(), screenY)
                )
            }
        }
    }
}
