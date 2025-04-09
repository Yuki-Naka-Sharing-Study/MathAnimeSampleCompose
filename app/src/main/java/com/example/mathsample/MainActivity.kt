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
            SlopeAngleChange()
        }
    }
}

@Composable
fun SlopeAngleChange() {
    var angle by remember { mutableStateOf(45f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("直線の傾き")

        // 角度のスライダー
        Slider(value = angle, onValueChange = { angle = it }, valueRange = -90f..90f, steps = 180, modifier = Modifier.fillMaxWidth())
        Text("角度: ${angle}°")

        // グラフ描画
        Canvas(modifier = Modifier.fillMaxSize().height(300.dp)) {
            val width = size.width
            val height = size.height
            val centerY = height / 2

            // 傾きに基づく直線の描画
            val slope = Math.tan(Math.toRadians(angle.toDouble())).toFloat()
            drawLine(Color.Green, Offset(0f, centerY), Offset(width, centerY - slope * width), strokeWidth = 3f)
        }
    }
}
