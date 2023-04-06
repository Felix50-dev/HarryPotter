package com.example.harrypotter.network

import retrofit2.http.GET
import com.example.harrypotter.data.models.Character

/**
 * A public interface that exposes the [getCharacters] method
 */
interface HarryPotterApiService {
    /**
     * Returns a [List] of [Character] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("characters")
    suspend fun getCharacters(): List<Character>
}
