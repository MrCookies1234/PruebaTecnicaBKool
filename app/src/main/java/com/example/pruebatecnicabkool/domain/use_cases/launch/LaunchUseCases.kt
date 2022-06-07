package com.example.pruebatecnicabkool.domain.use_cases.launch

/**
 * Wrapper class with all use cases. Used so we don't need to add individual dependencies to our viewmodels
 */
data class LaunchUseCases (
    val getAllLaunchesUseCase: GetAllLaunchesUseCase,
    val getLatestLaunchUseCase: GetLatestLaunchUseCase,
    val getLaunchDetailUseCase: GetLaunchDetailUseCase
    )


