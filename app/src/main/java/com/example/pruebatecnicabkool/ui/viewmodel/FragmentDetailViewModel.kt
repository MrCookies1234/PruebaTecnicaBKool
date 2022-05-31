package com.example.pruebatecnicabkool.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pruebatecnicabkool.domain.use_cases.launch.LaunchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FragmentDetailViewModel @Inject constructor(private val launchUseCases : LaunchUseCases) : ViewModel(){

}