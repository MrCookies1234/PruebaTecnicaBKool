package com.example.pruebatecnicabkool.domain.use_cases.ship

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.ship.Ship
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import javax.inject.Inject

class GetAllShipsUseCase @Inject constructor(private val spaceXRepo : SpaceXRepository) {

    suspend operator fun invoke() : Resource<List<Ship>> {
        return spaceXRepo.getShipList()
    }
}