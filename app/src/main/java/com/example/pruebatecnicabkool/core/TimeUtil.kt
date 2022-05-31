package com.example.pruebatecnicabkool.core

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun getLaunchDateFromTimeStamp(timestamp : Long) : String{
        val a = timestamp * 1000L
        val date = Date(a)
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

        return simpleDateFormat.format(date)
    }
}