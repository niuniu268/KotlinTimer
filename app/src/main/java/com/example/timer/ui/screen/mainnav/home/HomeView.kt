package com.example.timer.ui.screen.mainnav.home

import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.timer.config.Router
import com.example.timer.ui.components.LoadingProgress
import com.example.timer.ui.theme.TimerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.nio.file.WatchEvent

@Composable
fun HomeView() {
    val homeViewModel: HomeViewModel = viewModel()
    val timeState by homeViewModel.timeState.collectAsState()
    val uiState by homeViewModel.uiState.collectAsState()
    val dateState by homeViewModel.dateState.collectAsState()
    val screenOrientation by rememberUpdatedState(
        newValue = LocalConfiguration.current.orientation)
    val activity = LocalContext.current as Activity
    LaunchedEffect(screenOrientation){
        if (screenOrientation==1){
            homeViewModel.sendUIIntent(UIIntent.ChangeImmersionState(false))

        }else{
            homeViewModel.sendUIIntent(UIIntent.ChangeImmersionState(true))
        }
    }

    LaunchedEffect( uiState.immersionShow ){
        homeViewModel.sendUIIntent(UIIntent.LoadingImmersionState(true))
        if (uiState.immersionShow){
            activity.requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }else{
            activity.requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
        delay(300)
        homeViewModel.sendUIIntent(UIIntent.LoadingImmersionState(false))
    }



    Box (modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)){
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {
                    if (uiState.timeMode == TimeMode.PM){
                        OutlinedButton(onClick = { /*TODO*/ },
                            shape= RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)) {
                            Text(text = "AM")

                        }
                        OutlinedButton(onClick = { /*TODO*/ },
                            colors=ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            ),
                            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)) {
                            Text(text = "PM")

                        }

                    }else{
                        OutlinedButton(onClick = { /*TODO*/ },
                            colors=ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            ),
                            shape= RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)) {
                            Text(text = "AM")

                        }
                        OutlinedButton(onClick = { /*TODO*/ }, shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)) {
                            Text(text = "PM")

                        }
                    }


                }
                Switch(checked = uiState.immersionShow,
                    onCheckedChange = {
                        homeViewModel.sendUIIntent(UIIntent.ChangeImmersionState(!uiState.immersionShow))
                    })
            }
            Column (modifier = Modifier
                .fillMaxSize()
                .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ){
                Column {
                    Text(text = "${dateState.date}  ${dateState.weekday}", modifier=Modifier.padding(5.dp))
                    Row (verticalAlignment= Alignment.CenterVertically){
                        TimeTag(timeState.hour)
                        Text(text = ":")
                        TimeTag(timeState.minute)
                        Text(text = ":")
                        TimeTag(timeState.second)
                    }
                }

            }

        }
        if (uiState.loadingShow){
            LoadingProgress()
        }
    }
}
@Composable
fun TimeTag(specific:Int){

    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )){
        Text(text = specific.toString(), modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp))
    }

}
//@Composable
//@Preview
//fun HomeViewPreview(){
//    TimerTheme{
//        HomeView()
//    }
//}