package com.example.timer.ui.screen.mainnav.home


enum class TimeMode {
    PM,
    AM

}
data class UIState(
    var timeMode: TimeMode = TimeMode.PM,
    var immersionShow:Boolean=false
)

data class TimeState(
    var hour:Int=0,
    var minute:Int=0,
    var second:Int=0,
)

data class DateState(
    var date:String="",
    var weekday:String=""
)