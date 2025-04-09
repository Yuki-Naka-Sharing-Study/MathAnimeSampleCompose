package com.example.mathsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FibonacciAnimation()
        }
    }
}

@Composable
fun FibonacciAnimation() {
    val fibonacciNumbers = remember { mutableStateListOf(0, 1) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            for (i in 2..10) {
                delay(1000) // 1秒ごとに追加
                val nextValue = fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2]
                fibonacciNumbers.add(nextValue)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        fibonacciNumbers.forEachIndexed { index, number ->
            AnimatedNumber(number, index)
        }
    }
}

@Composable
fun AnimatedNumber(number: Int, index: Int) {
    val animatedValue = remember { Animatable(0f) }

    LaunchedEffect(number) {
        animatedValue.animateTo(number.toFloat(), animationSpec = tween(1000))
    }

    Text(
        text = "F(${index}) = ${animatedValue.value.toInt()}",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue,
        modifier = Modifier.padding(8.dp)
    )
}
