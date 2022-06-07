package com.example.pruebatecnicabkool.domain.use_cases.launch

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.launch.Launch
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import javax.inject.Inject

/**
 * Class responsible of getting the selected launch from the repository
 */
class GetLaunchDetailUseCase @Inject constructor(private val spaceXRepo : SpaceXRepository) {

    suspend operator fun invoke (id: String) : Resource<Launch>{
        return spaceXRepo.getLaunchDetail(id)
    }
}