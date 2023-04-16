package com.example.harrypotter.ui.mainscreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harrypotter.HarryPotterApplication
import com.example.harrypotter.data.CharactersRepository
import com.example.harrypotter.data.models.Character
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val name: String =
        checkNotNull(savedStateHandle[DetailsScreenDestination.characterIdArg])

    var character: Character by mutableStateOf(Character("", "", "", "", "", ""))
        private set

    init {
        viewModelScope.launch {
            character = getCharacter(name)
            Log.d("ThisCharacter", ":character " + character.name)
        }
    }

    private suspend fun getCharacter(name: String): Character {
        val characters = charactersRepository.getCharacters()
        Log.d("MyCharacterAre", ":characters: $characters")
        var myCharacter = Character("", "", "", "", "", "")
        for (character in characters) {
            if (character.name == name) myCharacter = character
        }
        return myCharacter
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HarryPotterApplication)
                val charactersRepository = application.container.charactersRepository
                DetailsScreenViewModel(
                    this.createSavedStateHandle(),
                    charactersRepository = charactersRepository
                )
            }
        }
    }
}