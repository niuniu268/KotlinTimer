package com.example.timer.ui.screen.mainnav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timer.R
import com.example.timer.config.MainPageRouter
import com.example.timer.ui.screen.mainnav.countdown.CountdownView
import com.example.timer.ui.screen.mainnav.home.HomeView
import com.example.timer.ui.screen.mainnav.stopwatch.StopwatchView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavView (){
    val navList = listOf(
        Pair("Main", R.drawable.home),
        Pair("Task", R.drawable.task),
        Pair("All",R.drawable.all)
    )
    var nowActiveIndex by remember {
        mutableStateOf(0)
    }
    val mainNavController = rememberNavController()
    Scaffold (modifier = Modifier.fillMaxSize(),bottomBar = {
        NavigationBar {
            navList.forEachIndexed{
                index, pair ->  
                NavigationBarItem(
                    selected = nowActiveIndex== index , 
                    onClick = { nowActiveIndex=when(index){
                        0->{
                            mainNavController.navigate(MainPageRouter.MAIN)
                            index
                        }
                        1->{
                            mainNavController.navigate(MainPageRouter.TASK)
                            index
                        }
                        2->{
                            mainNavController.navigate(MainPageRouter.ALL)
                            index
                        }

                        else -> {-1}
                    } },
                    icon = 
                    { 
                    Icon(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = pair.second),
                        contentDescription = pair.first,
                        tint=MaterialTheme.colorScheme.primary
                    )
                    },
                    label = {
                        Text(text = pair.first)
                    }
                )
            }
            

        }
    }){
        Box(modifier = Modifier.padding(it)){
            NavHost(navController = mainNavController, startDestination = MainPageRouter.MAIN ){
                composable(MainPageRouter.MAIN){
                    HomeView()
                }
                composable(MainPageRouter.TASK){
                    CountdownView()
                }
                composable(MainPageRouter.ALL){
                    StopwatchView()
                }
            }

        }
    }
}

