package com.example.pruebatecnicabkool.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtil {

    fun getLaunchDateFromTimeStamp(timestamp : Long) : String{
        if(timestamp == 0L) return "Not available"

        val date = Date(timestamp * 1000L)
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

        return simpleDateFormat.format(date)
    }
}