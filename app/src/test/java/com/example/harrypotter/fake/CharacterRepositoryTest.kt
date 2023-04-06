package com.example.harrypotter.fake

import com.example.harrypotter.data.CharactersRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() =
        runTest {
            val repository = CharactersRepository.DefaultHarryPotterRepository(
                harryPotterApiService = FakeApiService()
            )
            assertEquals(FakeDataSource.characterList, repository.getCharacters())
        }
}