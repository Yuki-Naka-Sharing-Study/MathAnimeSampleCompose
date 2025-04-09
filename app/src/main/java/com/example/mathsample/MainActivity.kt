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
            SpeedAndDistance()
        }
    }
}

@Composable
fun SpeedAndDistance() {
    var speed by remember { mutableFloatStateOf(5f) }
    var time by remember { mutableFloatStateOf(0f) }

    // 距離の計算
    val distance = speed * time

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("速さと距離")

        // 速さのスライダー
        Slider(value = speed, onValueChange = { speed = it }, valueRange = 1f..20f, steps = 20, modifier = Modifier.fillMaxWidth())
        Text("速さ: ${speed} m/s")

        // 時間のスライダー
        Slider(value = time, onValueChange = { time = it }, valueRange = 0f..10f, steps = 10, modifier = Modifier.fillMaxWidth())
        Text("時間: ${time} 秒")

        // グラフ描画
        Canvas(modifier = Modifier.fillMaxSize().height(300.dp)) {
            val width = size.width
            val height = size.height
            val centerX = width / 2
            val centerY = height / 2


            // 距離のアニメーション
            drawCircle(Color.Red, 20f, center = Offset(centerX + distance, centerY))
        }
    }
}
