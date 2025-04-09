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
            CirclePerimeter()
        }
    }
}

@Composable
fun CirclePerimeter() {
    var radius by remember { mutableFloatStateOf(5f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("円の周囲長: 周囲長 = 2πr")

        Slider(
            value = radius,
            onValueChange = { radius = it },
            valueRange = 1f..20f,
            steps = 20,
            modifier = Modifier.fillMaxWidth()
        )
        Text("半径 (r): $radius")

        val perimeter = 2 * Math.PI * radius

        Text("周囲長: $perimeter")

        Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            val centerX = size.width / 2
            val centerY = size.height / 2

            // 円の描画
            drawCircle(
                Color.Blue,
                radius = radius * 10f,
                center = Offset(centerX, centerY)
            )
        }
    }
}
