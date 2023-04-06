package com.example.harrypotter.data

import com.example.harrypotter.data.models.Character
import com.example.harrypotter.network.HarryPotterApiService

interface CharactersRepository {

    suspend fun getCharacters(): List<Character>

    class DefaultHarryPotterRepository(
        private val harryPotterApiService: HarryPotterApiService
    ) : CharactersRepository {

        override suspend fun getCharacters(): List<Character> {
            return harryPotterApiService.getCharacters()
        }

    }
}