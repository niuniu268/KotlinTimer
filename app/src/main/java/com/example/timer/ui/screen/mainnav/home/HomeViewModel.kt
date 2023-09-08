package com.example.timer.ui.screen.mainnav.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.Closeable
import java.time.LocalDateTime

class HomeViewModel:ViewModel() {
    private var _dateState = MutableStateFlow(DateState())
    val dateState = _dateState
    private var _timeState = MutableStateFlow(TimeState())
    val timeState = _timeState
    private var _uiState = MutableStateFlow(UIState())
    val uiState = _uiState

    init {
        initTime()
    }

    private fun initTime() {
        LocalDateTime.now().apply {
            _timeState.also {
                timeState.value=timeState.value.copy(
                    hour=hour,
                    minute=minute,
                    second=second
                )

            }
            _uiState.also{
                uiState.value=uiState.value.copy(
                    timeMode = if (hour>12) TimeMode.PM else TimeMode.AM
                )
            }

        }
        updateTime()
        updateWeekNdate()
    }

    private fun updateWeekNdate(){
        LocalDateTime.now().apply {
            _dateState.also{
                dateState.value=dateState.value.copy(
                    date = "${year}/${month.value}/${dayOfMonth}",
                    weekday = dayOfWeek.toString()
                )
            }

        }
    }

    private fun updateTime(){
        viewModelScope.launch (Dispatchers.IO){
            _timeState.apply {
                while (true){
                    val nowSecond = value.second+1

                    if (nowSecond==60){
                        value=value.copy(second = 0)
                        val nowMinute=value.minute+1
                        if (nowMinute==60){
                            value=value.copy(minute=0)
                            val nowHour=value.hour+1
                            val timeMode = if (nowHour>12){
                                TimeMode.PM
                            }else{
                                TimeMode.AM
                            }
                            _uiState.value=_uiState.value.copy(timeMode=timeMode)
                            value = if (nowHour==24){
                                updateWeekNdate()
                                value.copy(hour = 0)
                            }else{
                                value.copy(hour = nowHour)
                            }
                        }else{
                            value = value.copy(minute=nowMinute)
                        }
                    }else{
                        value=value.copy(second = nowSecond)
                    }
                    delay(1000)
                }
            }

        }
    }

}