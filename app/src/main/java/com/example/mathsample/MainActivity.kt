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
            SimilarityTransformation()
        }
    }
}

@Composable
fun SimilarityTransformation() {
    var scale by remember { mutableFloatStateOf(1f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("相似変換")

        // スケールのスライダー
        Slider(value = scale, onValueChange = { scale = it }, valueRange = 0.5f..2f, steps = 10, modifier = Modifier.fillMaxWidth())
        Text("スケール: $scale")

        // 図形描画
        Canvas(modifier = Modifier.fillMaxSize().height(300.dp)) {
            val width = size.width
            val height = size.height
            val centerX = width / 2
            val centerY = height / 2


            // 三角形の頂点を描画
            val p1 = Offset(centerX, centerY - 100f * scale)
            val p2 = Offset(centerX - 100f * scale, centerY + 100f * scale)
            val p3 = Offset(centerX + 100f * scale, centerY + 100f * scale)


            drawLine(Color.Blue, p1, p2, strokeWidth = 3f)
            drawLine(Color.Blue, p2, p3, strokeWidth = 3f)
            drawLine(Color.Blue, p3, p1, strokeWidth = 3f)
        }
    }
}
