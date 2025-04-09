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
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CenterLimitTheoremVisualizer()
        }
    }
}

@Composable
fun CenterLimitTheoremVisualizer() {
    var sampleSize by remember { mutableIntStateOf(30) }  // サンプルサイズ
    var numSamples by remember { mutableIntStateOf(1000) }  // サンプル数
    var sampleMeans by remember { mutableStateOf(generateSampleMeans(sampleSize, numSamples)) }  // サンプル平均リスト

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("中心極限定理の可視化", style = MaterialTheme.typography.headlineMedium)

        // サンプル数のスライダー
        Text("サンプルサイズ: $sampleSize")
        Slider(
            value = sampleSize.toFloat(),
            onValueChange = {
                sampleSize = it.toInt()
                sampleMeans = generateSampleMeans(sampleSize, numSamples)
            },
            valueRange = 10f..100f
        )

        // ヒストグラムの描画
        Canvas(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            val hist = generateHistogram(sampleMeans)
            val maxCount = hist.maxOf { it.value }

            // ヒストグラムを描画
            hist.forEachIndexed { index, entry ->
                val barHeight = (entry.value.toFloat() / maxCount) * size.height
                drawRect(
                    color = Color.Blue,
                    size = androidx.compose.ui.geometry.Size(10f, barHeight),
                    topLeft = Offset(index * 12f, size.height - barHeight)
                )
            }
        }
    }
}

// ランダムな値を使ってサンプル平均を生成
fun generateSampleMeans(sampleSize: Int, numSamples: Int): List<Float> {
    return List(numSamples) {
        val sample = List(sampleSize) { Random.nextFloat() }
        sample.average().toFloat()  // サンプル平均を計算
    }
}

// ヒストグラムを生成
fun generateHistogram(data: List<Float>, bins: Int = 50): List<Map.Entry<Int, Int>> {
    val min = data.minOrNull() ?: 0f
    val max = data.maxOrNull() ?: 1f
    val range = max - min
    val binWidth = range / bins

    val histogram = mutableMapOf<Int, Int>()
    data.forEach {
        val bin = ((it - min) / binWidth).toInt()
        histogram[bin] = histogram.getOrDefault(bin, 0) + 1
    }

    return histogram.entries.sortedBy { it.key }
}
