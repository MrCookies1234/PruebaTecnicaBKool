package com.example.pruebatecnicabkool.domain.use_cases.ship

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.ship.Ship
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import javax.inject.Inject

class GetShipDetailUseCase @Inject constructor(private val spaceXRepo : SpaceXRepository) {

    suspend operator fun invoke(id : String) : Resource<Ship>{
        return spaceXRepo.getShipDetail(id)
    }
}