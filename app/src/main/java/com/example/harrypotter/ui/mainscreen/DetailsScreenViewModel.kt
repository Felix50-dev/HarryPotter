package com.example.harrypotter.ui.mainscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harrypotter.HarryPotterApplication
import com.example.harrypotter.data.CharactersRepository
import com.example.harrypotter.data.models.Character

class DetailsScreenViewModel(private val charactersRepository: CharactersRepository): ViewModel() {

    var character: Character by mutableStateOf(Character("","","","","",""))
    private set

    private suspend fun getCharacter(name: String): Character {
        val characters = charactersRepository.getCharacters()
        var myCharacter = Character("","","","","","")
        for (character in characters){
            if (character.name == name) myCharacter = character
        }
        return myCharacter
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HarryPotterApplication)
                val charactersRepository = application.container.charactersRepository
                DetailsScreenViewModel(charactersRepository = charactersRepository)
            }
        }
    }
}