package com.example.timer.ui.screen.mainnav.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timer.config.Router
import com.example.timer.ui.theme.TimerTheme
import java.nio.file.WatchEvent

@Composable
fun HomeView() {
    Box (modifier = Modifier.background(MaterialTheme.colorScheme.background)){
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)){
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {
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
                Switch(checked = false, onCheckedChange = {})
            }
            Column (modifier = Modifier
                .fillMaxSize()
                .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ){
                Column {
                    Text(text = "2023/09/08 THU", modifier=Modifier.padding(5.dp))
                    Row (verticalAlignment= Alignment.CenterVertically){
                        TimeTag()
                        Text(text = ":")
                        TimeTag()
                        Text(text = ":")
                        TimeTag()
                    }
                }

            }

        }
    }
}
@Composable
fun TimeTag(){

    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )){
        Text(text = "12", modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp))
    }

}
@Composable
@Preview
fun HomeViewPreview(){
    TimerTheme{
        HomeView()
    }
}