package com.example.harrypotter.fake

import com.example.harrypotter.data.CharactersRepository
import com.example.harrypotter.data.models.Character

class FakeCharacterRepository: CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        return FakeDataSource.characterList
    }
}