package com.example.pruebatecnicabkool.data.model.ship

data class Ship(
    val abs: Int,
    val active: Boolean,
    val `class`: Int,
    val course_deg: Any,
    val home_port: String,
    val id: String,
    val image: String,
    val imo: Int,
    val last_ais_update: Any,
    val latitude: Any,
    val launches: List<String>,
    val legacy_id: String,
    val link: String,
    val longitude: Any,
    val mass_kg: Int,
    val mass_lbs: Int,
    val mmsi: Int,
    val model: Any,
    val name: String,
    val roles: List<String>,
    val speed_kn: Any,
    val status: String,
    val type: String,
    val year_built: Int
)