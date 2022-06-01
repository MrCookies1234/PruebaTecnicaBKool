package com.example.pruebatecnicabkool.di

import com.example.pruebatecnicabkool.core.Constants
import com.example.pruebatecnicabkool.data.data_source.spacexapi.SpaceXAPI
import com.example.pruebatecnicabkool.domain.use_cases.crew.CrewUseCases
import com.example.pruebatecnicabkool.domain.use_cases.crew.GetAllCrewUseCase
import com.example.pruebatecnicabkool.domain.use_cases.crew.GetCrewDetailUseCase
import com.example.pruebatecnicabkool.domain.use_cases.launch.GetAllLaunchesUseCase
import com.example.pruebatecnicabkool.domain.use_cases.launch.GetLatestLaunchUseCase
import com.example.pruebatecnicabkool.domain.use_cases.launch.GetLaunchDetailUseCase
import com.example.pruebatecnicabkool.domain.use_cases.launch.LaunchUseCases
import com.example.pruebatecnicabkool.domain.use_cases.rocket.GetAllRocketsUseCase
import com.example.pruebatecnicabkool.domain.use_cases.rocket.GetRocketDetailUseCase
import com.example.pruebatecnicabkool.domain.use_cases.rocket.RocketUseCases
import com.example.pruebatecnicabkool.domain.use_cases.ship.GetAllShipsUseCase
import com.example.pruebatecnicabkool.domain.use_cases.ship.GetShipDetailUseCase
import com.example.pruebatecnicabkool.domain.use_cases.ship.ShipUseCases
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import com.example.pruebatecnicabkool.repository.SpaceXRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.intellij.lang.annotations.PrintFormat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSpaceXRepository(api: SpaceXAPI): SpaceXRepository {
        return SpaceXRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideSpaceXAPI(): SpaceXAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(SpaceXAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideLaunchUseCases(repo: SpaceXRepository): LaunchUseCases {
        return LaunchUseCases(
            getAllLaunchesUseCase = GetAllLaunchesUseCase(repo),
            getLatestLaunchUseCase = GetLatestLaunchUseCase(repo),
            getLaunchDetailUseCase = GetLaunchDetailUseCase(repo)
        )
    }

    @Provides
    @Singleton
    fun provideRocketUseCases(repo : SpaceXRepository) : RocketUseCases{
        return RocketUseCases(
            getRocketDetailUseCase = GetRocketDetailUseCase(repo),
            getAllRocketsUseCase = GetAllRocketsUseCase(repo)
        )
    }

    @Provides
    @Singleton
    fun provideCrewUseCases(repo: SpaceXRepository) : CrewUseCases{
        return CrewUseCases(
            getCrewDetailUseCase = GetCrewDetailUseCase(repo),
            getAllCrewUseCase = GetAllCrewUseCase(repo)
        )
    }

    @Provides
    @Singleton
    fun provideShipUseCases(repo: SpaceXRepository) : ShipUseCases {
        return ShipUseCases(
            getShipDetailUseCase = GetShipDetailUseCase(repo),
            getAllShipsUseCase = GetAllShipsUseCase(repo)
        )
    }
}