package com.example.timer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timer.ui.theme.TimerTheme
import kotlin.concurrent.timerTask
import androidx.compose.ui.layout.VerticalAlignmentLine as VerticalAlignmentLine1

@Composable
fun LoadingProgress( title:String = "Loading"){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.White.copy(alpha = 0.5f))
        .clickable { },
        verticalArrangement=Arrangement.Center,
        horizontalAlignment=Alignment.CenterHorizontally
        ){
        CircularProgressIndicator()
        Text(text = title, modifier = Modifier.padding(10.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewLoading(){
    TimerTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(color = Color.White)
        ) {
            LoadingProgress()

        }

    }
}


