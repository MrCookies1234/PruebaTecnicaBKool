package com.example.pruebatecnicabkool.data.model.launch

data class LaunchDetailEntry(
    val id: String? = "",
    val date_unix: Long? = 0,
    val failures: List<Any>? = emptyList(),
    val static_fire_date_unix: Long? = 0,
    val success: Boolean? = true,
    val upcoming: Boolean? = false,
    val img : String? = "",
    val details : String? = "",
    val name : String? = ""
)
