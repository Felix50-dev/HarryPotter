package com.example.harrypotter.fake

import com.example.harrypotter.ui.mainscreen.MainScreenUiState
import com.example.harrypotter.ui.mainscreen.MainScreenViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

//@OptIn(ExperimentalCoroutinesApi::class)
//class AmphibiansViewModelTest {
//
//    @get:Rule
//    val testDispatcher = TestDispatcherRule()
//
//    @Test
//    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
//        runTest {
//            val amphibiansViewModel = MainScreenViewModel(
//                charactersRepository = FakeCharacterRepository
//            )
//            assertEquals(
//                MainScreenUiState.Success(FakeDataSource.characterList),
//                amphibiansViewModel.mainScreenUiState
//            )
//        }
//}