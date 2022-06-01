package com.example.pruebatecnicabkool.repository

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.data_source.spacexapi.SpaceXAPI
import com.example.pruebatecnicabkool.data.model.crew.Crew
import com.example.pruebatecnicabkool.data.model.launch.Launch
import com.example.pruebatecnicabkool.data.model.rocket.Rocket
import com.example.pruebatecnicabkool.data.model.ship.Ship
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SpaceXRepositoryImpl @Inject constructor(private val api : SpaceXAPI) : SpaceXRepository {

    override suspend fun getLatestLaunch() : Resource<Launch> {
        val response = try{
            api.getLatestLaunch()
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }

    override suspend fun getLaunchDetail(id : String) : Resource<Launch> {
        val response = try{
            api.getLaunchDetail(id)
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }

    override suspend fun getLaunchList() : Resource<List<Launch>> {
        val response = try{
            api.getLaunchList()
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }

    override suspend fun getRocketDetail(id: String): Resource<Rocket> {
        val response = try{
            api.getRocketDetail(id)
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }

    override suspend fun getRocketList(): Resource<List<Rocket>> {
        val response = try{
            api.getRocketList()
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }

    override suspend fun getCrewDetail(id: String): Resource<Crew> {
        val response = try{
            api.getCrewDetail(id)
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }

    override suspend fun getShipDetail(id: String): Resource<Ship> {
        val response = try{
            api.getShipDetail(id)
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }

    override suspend fun getShipList(): Resource<List<Ship>> {
        val response = try{
            api.getShipList()
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }

    override suspend fun getCrewList(): Resource<List<Crew>> {
        val response = try{
            api.getCrewList()
        }catch (e : Exception){
            return Resource.Error(null,e.message!!)
        }
        return Resource.Success(response)
    }
}