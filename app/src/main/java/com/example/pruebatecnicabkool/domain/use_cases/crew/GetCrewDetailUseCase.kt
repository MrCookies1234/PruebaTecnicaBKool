package com.example.pruebatecnicabkool.domain.use_cases.crew

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.crew.Crew
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import javax.inject.Inject

class GetCrewDetailUseCase @Inject constructor(private val spaceXRepo : SpaceXRepository) {

    suspend operator fun invoke(id: String) : Resource<Crew>{
        return spaceXRepo.getCrewDetail(id)
    }
}