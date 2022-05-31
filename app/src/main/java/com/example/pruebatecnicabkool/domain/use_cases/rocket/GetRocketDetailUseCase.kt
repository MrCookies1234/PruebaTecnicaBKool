package com.example.pruebatecnicabkool.domain.use_cases.rocket

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.rocket.Rocket
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import javax.inject.Inject

class GetRocketDetailUseCase @Inject constructor(private val spaceXRepo : SpaceXRepository) {

     suspend operator fun invoke(id : String) : Resource<Rocket> {
        return spaceXRepo.getRocketDetail(id)
    }
}