package com.example.pruebatecnicabkool.data.data_source.spacexapi

import com.example.pruebatecnicabkool.data.model.launch.Launch
import com.example.pruebatecnicabkool.data.model.rocket.Rocket
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXAPI {

    @GET("launches")
    suspend fun getLaunchList() : List<Launch>
    @GET("launches/latest")
    suspend fun getLatestLaunch() : Launch
    @GET("launches/{id}")
    suspend fun getLaunchDetail(@Path("id")id : String) : Launch
    @GET("rockets/{id}")
    suspend fun getRocketDetail(@Path("id")id : String) : Rocket
    @GET("rockets")
    suspend fun getRocketList() : List<Rocket>
}