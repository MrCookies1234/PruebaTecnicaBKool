package com.example.pruebatecnicabkool.domain.use_cases.crew

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.crew.Crew
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import javax.inject.Inject

class GetAllCrewUseCase @Inject constructor(private val spaceXRepo : SpaceXRepository) {

    suspend operator fun invoke() : Resource<List<Crew>> {
        return spaceXRepo.getCrewList()
    }
}