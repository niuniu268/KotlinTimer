package com.example.timer.ui.screen.start

import android.content.res.Resources.Theme
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.timer.config.Router
import com.example.timer.ui.theme.TimerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StartPageView(appNavController:NavHostController?=null) {
//    LaunchedEffect(Unit){
//        delay(1500)
//        appNavController.navigate(Router.MAIN_NAV)
//    }
    var lineAplha by remember {
        mutableStateOf(0f)
    }
    val animatable = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    val circleColor=MaterialTheme.colorScheme.primaryContainer
    val lineColor=MaterialTheme.colorScheme.onPrimaryContainer
    var contentVisible by remember {
        mutableStateOf(false)
    }

    var iconVisible by remember {
        mutableStateOf(false)
    }
    var showLine by remember {
        mutableStateOf(false)
    }
    val canvasRotation by animateFloatAsState(targetValue = if (lineAplha==1f) 360f else 0f)

    LaunchedEffect(Unit){
        launch {
            delay(500)
            iconVisible = true
            delay(1500)
            showLine = true
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(500, easing = LinearEasing)
            )

        }
        delay(2000)
        contentVisible=true
    }

    Column (modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        AnimatedVisibility(visible = contentVisible,
            enter = expandVertically (expandFrom = Alignment.Bottom) + fadeIn(
                initialAlpha = 0.1f
            )
        ) {
            Canvas(modifier = Modifier
                .size(100.dp)
                .rotate(canvasRotation)){
                drawCircle(color = circleColor,
                    radius = size.minDimension/2,
                    center= Offset(size.width/2, size.height/2)
                )
                if(showLine){
                    lineAplha=animatable.value

                    drawLine(color = lineColor,
                        start= Offset(size.width/2, size.height/2),
                        end= Offset(size.width/2,(size.height/2 + size.height/3)),
                        strokeWidth = 3.dp.toPx(),
                        cap= StrokeCap.Square,
                        alpha = lineAplha
                        )

                }

            }

        }
        AnimatedVisibility(visible = contentVisible,
            enter = expandVertically (expandFrom = Alignment.Bottom) + fadeIn(
                initialAlpha = 0.1f
            )
        ) {
            Text(text="timer", fontWeight = FontWeight.Bold)

        }

    }
    LaunchedEffect(Unit ){
        delay(3000)
        if (appNavController != null) {
            appNavController.navigate(Router.MAIN_NAV)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun StartPagePreview(){
    TimerTheme {
        StartPageView()
    }

}

