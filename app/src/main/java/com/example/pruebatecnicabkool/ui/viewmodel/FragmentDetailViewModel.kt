package com.example.pruebatecnicabkool.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.crew.Crew
import com.example.pruebatecnicabkool.data.model.launch.LaunchDetailEntry
import com.example.pruebatecnicabkool.data.model.ship.Ship
import com.example.pruebatecnicabkool.domain.use_cases.crew.CrewUseCases
import com.example.pruebatecnicabkool.domain.use_cases.launch.LaunchUseCases
import com.example.pruebatecnicabkool.domain.use_cases.ship.ShipUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FragmentDetailViewModel @Inject constructor(
    private val launchUseCases: LaunchUseCases,
    private val crewUseCases: CrewUseCases,
    private val shipUseCases: ShipUseCases,
    state : SavedStateHandle
) : ViewModel() {

    private val _launchDetail = MutableStateFlow(LaunchDetailEntry())
    val launchDetail get() = _launchDetail.asStateFlow()

    private val cachedShipList = arrayListOf<Ship>()
    private val cachedCrewList = arrayListOf<Crew>()

    init {
        loadAllShipList()
        loadAllCrewList()
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
                        youtube_link = resultLaunches.data.links.youtube_id,
                        ships = getShips(resultLaunches.data.ships),
                        static_fire_date_unix = resultLaunches.data.static_fire_date_unix,
                        success = resultLaunches.data.success,
                        upcoming = resultLaunches.data.upcoming,
                        crew = getCrew(resultLaunches.data.crew)
                    )
                    _launchDetail.value = entry
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

    private fun getShips(ids : List<String>) : ArrayList<Ship>{
        val listOfShips = arrayListOf<Ship>()
        for (id in ids){
            listOfShips.add(cachedShipList.find {
                it.id == id
            }!!)
        }
        return listOfShips
    }

    private fun getCrew(ids : List<String>) : ArrayList<Crew>{
        val listOfCrew = arrayListOf<Crew>()
        for (id in ids){
            listOfCrew.add(cachedCrewList.find {
                it.id == id
            }!!)
        }
        return listOfCrew
    }

    private fun loadAllCrewList(){
        viewModelScope.launch {
            when (val resultCrew = crewUseCases.getAllCrewUseCase.invoke()) {
                is Resource.Success -> {
                    resultCrew.data!!.forEach{
                        cachedCrewList.add(it)
                    }
                }
                is Resource.Error -> {
                    Timber.e(resultCrew.message)
                }
                is Resource.Loading -> {
                    Timber.d("Loading")
                }
            }

        }
    }

    private fun loadAllShipList(){
        viewModelScope.launch {
            when (val resultShip = shipUseCases.getAllShipsUseCase.invoke()) {
                is Resource.Success -> {
                    resultShip.data!!.forEach {
                       cachedShipList.add(it)
                    }
                }
                is Resource.Error -> {
                    Timber.e(resultShip.message)
                }
                is Resource.Loading -> {
                    Timber.d("Loading")
                }
            }

        }

    }

}