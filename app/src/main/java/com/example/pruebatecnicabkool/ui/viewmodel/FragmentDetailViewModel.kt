package com.example.pruebatecnicabkool.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.core.TimeUtil
import com.example.pruebatecnicabkool.data.model.launch.LaunchDetailEntry
import com.example.pruebatecnicabkool.domain.use_cases.launch.LaunchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FragmentDetailViewModel @Inject constructor(
    private val launchUseCases: LaunchUseCases,
    state : SavedStateHandle
) : ViewModel() {

    private val _launchDetail = MutableStateFlow(LaunchDetailEntry())
    val launchDetail get() = _launchDetail.asStateFlow()

    private val _youtubeIdState = MutableStateFlow("")
    val youtubeIdState get() = _youtubeIdState.asStateFlow()

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
                        static_fire_date_unix = TimeUtil.getLaunchDateFromTimeStamp(resultLaunches.data.static_fire_date_unix),
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

}