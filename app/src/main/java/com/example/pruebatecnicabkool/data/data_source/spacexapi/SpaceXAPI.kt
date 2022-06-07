package com.example.pruebatecnicabkool.data.data_source.spacexapi

import com.example.pruebatecnicabkool.data.model.launch.Launch
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * SpaceX retrofit API with base url "https://api.spacexdata.com/v4/"
 */

interface SpaceXAPI {

    /**
     * Method to get a list of all launches.
     * @return List of all launches.
     */
    @GET("launches")
    suspend fun getLaunchList() : List<Launch>

    /**
     * Method to get the latest launch.
     * @return Latest launch.
     */
    @GET("launches/latest")
    suspend fun getLatestLaunch() : Launch

    /**
     * Method to get the selected launch.
     * @param id The id to look for.
     * @return Latest launch.
     * */
    @GET("launches/{id}")
    suspend fun getLaunchDetail(@Path("id")id : String) : Launch

}