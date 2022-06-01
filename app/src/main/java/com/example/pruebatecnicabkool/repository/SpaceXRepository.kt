package com.example.pruebatecnicabkool.repository

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.crew.Crew
import com.example.pruebatecnicabkool.data.model.launch.Launch
import com.example.pruebatecnicabkool.data.model.rocket.Rocket
import com.example.pruebatecnicabkool.data.model.ship.Ship

interface SpaceXRepository {

    suspend fun getLatestLaunch() : Resource<Launch>
    suspend fun getLaunchDetail(id : String) : Resource<Launch>
    suspend fun getLaunchList() : Resource<List<Launch>>
    suspend fun getRocketDetail(id : String) : Resource<Rocket>
    suspend fun getRocketList() : Resource<List<Rocket>>
    suspend fun getCrewDetail(id: String) : Resource<Crew>
    suspend fun getShipDetail(id: String) : Resource<Ship>
    suspend fun getCrewList() : Resource<List<Crew>>
    suspend fun getShipList() : Resource<List<Ship>>
}