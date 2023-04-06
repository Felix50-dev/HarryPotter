package com.example.harrypotter.data.models
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: String,
    val name: String,
    val house: String,
    val image: String,
    //val wand: String,
    val alternateNames: String,
    val actor: String
)


