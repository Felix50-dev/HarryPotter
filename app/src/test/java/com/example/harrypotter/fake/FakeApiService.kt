package com.example.harrypotter.fake

import com.example.harrypotter.network.HarryPotterApiService
import com.example.harrypotter.data.models.Character

class FakeApiService: HarryPotterApiService {
    override suspend fun getCharacters(): List<Character> {
        return FakeDataSource.characterList
    }
}