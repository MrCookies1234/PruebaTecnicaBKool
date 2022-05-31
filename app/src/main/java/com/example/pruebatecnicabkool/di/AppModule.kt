package com.example.pruebatecnicabkool.di

import com.example.pruebatecnicabkool.core.Constants
import com.example.pruebatecnicabkool.data.data_source.spacexapi.SpaceXAPI
import com.example.pruebatecnicabkool.domain.use_cases.GetAllLaunchesUseCase
import com.example.pruebatecnicabkool.domain.use_cases.GetLatestLaunchUseCase
import com.example.pruebatecnicabkool.domain.use_cases.GetLaunchDetailUseCase
import com.example.pruebatecnicabkool.domain.use_cases.LaunchUseCases
import com.example.pruebatecnicabkool.repository.SpaceXRepository
import com.example.pruebatecnicabkool.repository.SpaceXRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}