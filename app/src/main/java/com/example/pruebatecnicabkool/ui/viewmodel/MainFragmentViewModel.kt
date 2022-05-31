package com.example.pruebatecnicabkool.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pruebatecnicabkool.domain.use_cases.LaunchUseCases
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val launchUseCases: LaunchUseCases) : ViewModel(){

}