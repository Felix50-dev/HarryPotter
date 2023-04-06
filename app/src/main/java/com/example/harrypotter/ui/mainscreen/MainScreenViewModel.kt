package com.example.harrypotter.ui.mainscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harrypotter.HarryPotterApplication
import com.example.harrypotter.data.CharactersRepository
import com.example.harrypotter.data.models.Character
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainScreenUiState {
    data class Success(val characters: List<Character>) : MainScreenUiState
    object Error : MainScreenUiState
    object Loading : MainScreenUiState
}

class MainScreenViewModel(private val charactersRepository: CharactersRepository): ViewModel() {

    /** The mutable State that stores the status of the most recent request */
    var mainScreenUiState: MainScreenUiState by mutableStateOf(MainScreenUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API
     */
    private fun getMarsPhotos() {
        viewModelScope.launch {
            mainScreenUiState = try {
                MainScreenUiState.Success(charactersRepository.getCharacters())
            } catch (e: IOException) {
                MainScreenUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HarryPotterApplication)
                val charactersRepository = application.container.charactersRepository
                MainScreenViewModel(charactersRepository = charactersRepository)
            }
        }
    }
}
