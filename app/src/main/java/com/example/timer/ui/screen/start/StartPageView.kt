package com.example.timer.ui.screen.start

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.timer.config.Router
import kotlinx.coroutines.delay

@Composable
fun StartPageView(appNavController:NavHostController) {
    LaunchedEffect(Unit){
        delay(1500)
        appNavController.navigate(Router.MAIN_NAV)
    }
    Text(text = "the start page")
}