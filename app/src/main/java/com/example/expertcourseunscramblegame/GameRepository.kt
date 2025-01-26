package com.example.expertcourseunscramblegame

interface GameRepository {

    fun shuffledWord(): String
    fun originalWord(): String
    fun next()
}
