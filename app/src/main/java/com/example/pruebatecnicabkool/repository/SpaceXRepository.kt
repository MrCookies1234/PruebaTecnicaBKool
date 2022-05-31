package com.example.pruebatecnicabkool.repository

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.launch.Launch

interface SpaceXRepository {

    suspend fun getLatestLaunch() : Resource<Launch>
    suspend fun getLaunchDetail(id : String) : Resource<Launch>
    suspend fun getLaunchList() : Resource<List<Launch>>
}