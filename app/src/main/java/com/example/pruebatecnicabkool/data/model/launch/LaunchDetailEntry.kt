package com.example.pruebatecnicabkool.data.model.launch

import com.example.pruebatecnicabkool.data.model.crew.Crew
import com.example.pruebatecnicabkool.data.model.ship.Ship

data class LaunchDetailEntry(
    val id: String = "",
    val date_unix: Long = 0,
    val failures: List<Any> = emptyList(),
    val youtube_link: String = "",
    val ships: ArrayList<Ship> = arrayListOf(),
    val static_fire_date_unix: Long = 0,
    val success: Boolean = true,
    val upcoming: Boolean = false,
    val crew: ArrayList<Crew> = arrayListOf()
)
