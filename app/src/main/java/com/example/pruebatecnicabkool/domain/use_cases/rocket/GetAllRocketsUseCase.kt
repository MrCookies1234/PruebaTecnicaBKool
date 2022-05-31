package com.example.pruebatecnicabkool.domain.use_cases.rocket

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.rocket.Rocket
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import javax.inject.Inject

class GetAllRocketsUseCase @Inject constructor(private val spaceXRepo : SpaceXRepository) {

    suspend operator fun invoke() : Resource<List<Rocket>>{
        return spaceXRepo.getRocketList()
    }

}