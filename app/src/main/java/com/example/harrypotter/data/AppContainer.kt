package com.example.harrypotter.data

import com.example.harrypotter.network.HarryPotterApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {

    val charactersRepository: CharactersRepository

}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://hp-api.onrender.com/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : HarryPotterApiService by lazy {
        retrofit.create(HarryPotterApiService::class.java)
    }

    override val charactersRepository: CharactersRepository by lazy {
        CharactersRepository.DefaultHarryPotterRepository(retrofitService)
    }

}