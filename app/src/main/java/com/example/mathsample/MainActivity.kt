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
            LinearAlgebraVisualizer()
        }
    }
}

@Composable
fun LinearAlgebraVisualizer() {
    var angle by remember { mutableFloatStateOf(0f) }  // 回転角度（度数法）
    var scaleX by remember { mutableFloatStateOf(1f) } // X軸のスケール
    var scaleY by remember { mutableFloatStateOf(1f) } // Y軸のスケール

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("線形代数：行列変換の視覚化", style = MaterialTheme.typography.headlineSmall)

        // 行列変換の適用
        MatrixTransformationCanvas(angle, scaleX, scaleY)

        // スライダーで角度変更
        Text("回転角: ${angle.toInt()}°")
        Slider(
            value = angle,
            onValueChange = { angle = it },
            valueRange = 0f..360f
        )

        // スケール変更
        Text("X軸のスケール: $scaleX")
        Slider(
            value = scaleX,
            onValueChange = { scaleX = it },
            valueRange = 0.5f..2f
        )

        Text("Y軸のスケール: $scaleY")
        Slider(
            value = scaleY,
            onValueChange = { scaleY = it },
            valueRange = 0.5f..2f
        )
    }
}

@Composable
fun MatrixTransformationCanvas(angle: Float, scaleX: Float, scaleY: Float) {
    Canvas(
        modifier = Modifier.fillMaxWidth().height(300.dp)
    ) {
        val center = Offset(size.width / 2, size.height / 2)

        // ベクトルの原点
        val origin = Offset(center.x, center.y)

        // 元のベクトル (50, 50)
        val baseVector = Offset(50f, -50f)

        // 行列変換を適用
        val rad = Math.toRadians(angle.toDouble()) // 角度をラジアンに変換
        val cos = Math.cos(rad).toFloat()
        val sin = Math.sin(rad).toFloat()

        // 回転とスケールを適用した新しいベクトル
        val transformedVector = Offset(
            x = (baseVector.x * cos - baseVector.y * sin) * scaleX,
            y = (baseVector.x * sin + baseVector.y * cos) * scaleY
        )

        // 原点からの矢印を描画（元のベクトル）
        drawLine(Color.Gray, origin, origin + baseVector, strokeWidth = 5f)

        // 変換後のベクトルを描画（赤色）
        drawLine(Color.Red, origin, origin + transformedVector, strokeWidth = 5f)
    }
}
