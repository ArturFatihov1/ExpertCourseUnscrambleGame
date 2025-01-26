package com.example.expertcourseunscramblegame

import com.example.expertcourseunscramblegame.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding: ActivityMainBinding): Unit = throw IllegalStateException("handle this")

    data class Initial(val shuffledWord: String) : GameUiState
    data class Insufficient(val shuffledWord: String) : GameUiState
    data class Sufficient(val shuffledWord: String) : GameUiState
    data class Correct(val shuffledWord: String) : GameUiState
    data class Incorrect(val shuffledWord: String) : GameUiState
}
