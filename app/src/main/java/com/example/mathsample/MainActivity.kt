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
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PythagoreanTheorem()
        }
    }
}

@Composable
fun PythagoreanTheorem() {
    var a by remember { mutableFloatStateOf(3f) }
    var b by remember { mutableFloatStateOf(4f) }
    val c = sqrt(a * a + b * b)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("ピタゴラスの定理: a² + b² = c²")

        Slider(
            value = a,
            onValueChange = { a = it },
            valueRange = 1f..20f,
            steps = 20,
            modifier = Modifier.fillMaxWidth()
        )
        Text("a = $a")

        Slider(
            value = b,
            onValueChange = { b = it },
            valueRange = 1f..20f,
            steps = 20,
            modifier = Modifier.fillMaxWidth()
        )
        Text("b = $b")

        Text("c (斜辺) = $c")

        Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            val width = size.width
            val height = size.height

            // 三角形の描画
            val p1 = Offset(width / 2, height / 2)
            val p2 = Offset(width / 2 + a * 10, height / 2)
            val p3 = Offset(width / 2, height / 2 - b * 10)

            drawLine(Color.Red, p1, p2, strokeWidth = 3f)
            drawLine(Color.Green, p1, p3, strokeWidth = 3f)
            drawLine(Color.Blue, p2, p3, strokeWidth = 3f)
        }
    }
}
