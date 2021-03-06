package com.example.pruebatecnicabkool.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.core.TimeUtil
import com.example.pruebatecnicabkool.data.model.launch.LaunchListEntry
import com.example.pruebatecnicabkool.domain.use_cases.launch.LaunchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val launchUseCases: LaunchUseCases
) : ViewModel() {

    private val _launchEntries = MutableStateFlow(emptyList<LaunchListEntry>())
    val launchEntries get() = _launchEntries.asStateFlow()

    init {
        loadAllLaunchList()
    }

    private fun loadAllLaunchList() {
        viewModelScope.launch {
            when (val resultLaunches = launchUseCases.getAllLaunchesUseCase.invoke()) {
                is Resource.Success -> {
                    val entries = resultLaunches.data!!.map {
                        LaunchListEntry(
                            it.id,
                            TimeUtil.getLaunchDateFromTimeStamp(it.static_fire_date_unix),
                            it.success,
                            it.name
                        )
                    }
                    _launchEntries.value = entries
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