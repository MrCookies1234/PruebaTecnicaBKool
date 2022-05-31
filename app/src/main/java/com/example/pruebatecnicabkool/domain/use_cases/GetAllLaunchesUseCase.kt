package com.example.pruebatecnicabkool.domain.use_cases

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.launch.Launch
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import javax.inject.Inject

class GetAllLaunchesUseCase @Inject constructor(private val spaceXRepo : SpaceXRepository) {

    suspend operator fun invoke() : Resource<List<Launch>>{
        return spaceXRepo.getLaunchList()
    }
}