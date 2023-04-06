package com.example.harrypotter.fake
import com.example.harrypotter.data.models.Character

object FakeDataSource {
    const val nameOne = "name one"
    const val nameTwo = "name two"
    const val typeOne = "type one"
    const val typeTwo = "type two"
    const val descOne = "first long desc"
    const val descTwo = "second long desc"
    const val imgSrcOne = "url.1"
    const val imgSrcTwo = "url.2"

    val characterList = listOf(Character(nameOne, typeOne, descOne, imgSrcOne,"",""), Character(
        nameTwo, typeTwo, descTwo, imgSrcTwo,"","")
    )
}