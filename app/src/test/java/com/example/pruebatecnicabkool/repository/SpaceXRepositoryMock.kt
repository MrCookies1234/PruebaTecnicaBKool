package com.example.pruebatecnicabkool.repository

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.launch.Launch

class SpaceXRepositoryMock(list : List<Launch>) : SpaceXRepository {

    private val listOfLaunches = list

    override suspend fun getLatestLaunch(): Resource<Launch> {
        return if(listOfLaunches.isNotEmpty()) Resource.Success(listOfLaunches.last()) else Resource.Error(null, "The list is empty")
    }

    override suspend fun getLaunchDetail(id: String): Resource<Launch> {
        val element = listOfLaunches.find { it.id == id }
        return if (element == null) Resource.Error(null,"Element not found") else Resource.Success(element)
    }

    override suspend fun getLaunchList(): Resource<List<Launch>> {
        return if(listOfLaunches.isNotEmpty()) Resource.Success(listOfLaunches) else Resource.Error(null, "The list is empty")
    }

}