package com.example.pruebatecnicabkool.data.model.launch

data class Launch(
    val auto_update: Boolean,
    val capsules: List<String>,
    val cores: List<Core>,
    val crew: List<String>,
    val date_local: String,
    val date_precision: String,
    val date_unix: Long,
    val date_utc: String,
    val details: String,
    val failures: List<Any>,
    val fairings: Any,
    val flight_number: Int,
    val id: String,
    val launchpad: String,
    val links: Links,
    val name: String,
    val net: Boolean,
    val payloads: List<String>,
    val rocket: String,
    val ships: List<String>,
    val static_fire_date_unix: Long,
    val static_fire_date_utc: String,
    val success: Boolean,
    val tdb: Boolean,
    val upcoming: Boolean,
    val window: Int
)