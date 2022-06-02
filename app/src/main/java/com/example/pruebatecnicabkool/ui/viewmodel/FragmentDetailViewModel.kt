package com.example.pruebatecnicabkool.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.launch.LaunchDetailEntry
import com.example.pruebatecnicabkool.data.model.rocket.Rocket
import com.example.pruebatecnicabkool.domain.use_cases.launch.LaunchUseCases
import com.example.pruebatecnicabkool.domain.use_cases.rocket.RocketUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FragmentDetailViewModel @Inject constructor(
    private val launchUseCases: LaunchUseCases,
    private val rocketUseCases : RocketUseCases,
    state : SavedStateHandle
) : ViewModel() {

    private val _launchDetail = MutableStateFlow(LaunchDetailEntry())
    val launchDetail get() = _launchDetail.asStateFlow()

    private val _youtubeIdState = MutableStateFlow("")
    val youtubeIdState get() = _youtubeIdState.asStateFlow()

    private val cachedRocketList = arrayListOf<Rocket>()

    init {
        state.get<String>("launchId")?.let { getLaunchDetailInfo(it) }
    }

    private fun getLaunchDetailInfo(id: String) {
        viewModelScope.launch {
            when (val resultLaunches = launchUseCases.getLaunchDetailUseCase.invoke(id)) {
                is Resource.Success -> {
                    val entry = LaunchDetailEntry(
                        id = resultLaunches.data!!.id,
                        date_unix = resultLaunches.data.date_unix,
                        failures = resultLaunches.data.failures,
                        static_fire_date_unix = resultLaunches.data.static_fire_date_unix,
                        success = resultLaunches.data.success,
                        upcoming = resultLaunches.data.upcoming,
                        img = resultLaunches.data.links.patch.small,
                        details = resultLaunches.data.details,
                        name = resultLaunches.data.name
                    )
                    _launchDetail.value = entry
                    _youtubeIdState.value = resultLaunches.data.links.youtube_id
                }
                is Resource.Error -> {
                    Timber.e(resultLaunches.message)
                }
                is Resource.Loading -> {
                    Timber.d("Loading")
                }
            }
        }
    }

    private fun loadAllRocketList(){
        viewModelScope.launch {
            when (val resultRockets = rocketUseCases.getAllRocketsUseCase.invoke()) {
                is Resource.Success -> {
                    resultRockets.data!!.forEach {
                        cachedRocketList.add(it)
                    }
                }
                is Resource.Error -> {
                    Timber.e(resultRockets.message)
                }
                is Resource.Loading -> {
                    Timber.d("Loading")
                }
            }

        }
    }

    private fun getRocketFromId(id: String): String {
        return cachedRocketList.find {
            it.id == id
        }!!.name
    }

}