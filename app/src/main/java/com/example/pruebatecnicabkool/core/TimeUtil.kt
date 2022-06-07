package com.example.pruebatecnicabkool.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * This class contains methods to convert the time returned from the API
 * to a readable format. It's an object so we don't have to instantiate the class.
 */
object TimeUtil {

    /**
     * Converts the api response to a string formatted as "yyyy-MM-dd"
     * @param timestamp Unix timeStamp in seconds
     * @return "Not available" if we don't have a valid response. Formatted String if we do.
     */
    fun getLaunchDateFromTimeStamp(timestamp : Long) : String{
        if(timestamp == 0L) return "Not available"

        val date = Date(timestamp * 1000L)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return simpleDateFormat.format(date)
    }
}