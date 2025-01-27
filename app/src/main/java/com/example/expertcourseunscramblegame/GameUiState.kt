package com.example.expertcourseunscramblegame

import android.view.View
import com.example.expertcourseunscramblegame.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val shuffledValue: String,
        private val inputUiState: InputUiState,
        private val skipVisibility: Int,
        private val checkUiState: CheckUiState,
        private val nextVisibility: Int
    ) : GameUiState {
        override fun update(binding: ActivityMainBinding) = with(binding) {
            shuffledWordTextView.text = shuffledValue
            inputUiState.update(binding.inputLayout, binding.inputEditText)
            skipButton.visibility = skipVisibility
            checkUiState.update(checkButton)
            nextButton.visibility = nextVisibility
        }
    }


    data class Initial(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.Initial,
        skipVisibility = View.VISIBLE,
        CheckUiState.Disabled,
        nextVisibility = View.GONE
    )

    data class Insufficient(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.Insufficient,
        skipVisibility = View.VISIBLE,
        CheckUiState.Disabled,
        nextVisibility = View.GONE
    )

    data class Sufficient(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.Sufficient,
        skipVisibility = View.VISIBLE,
        CheckUiState.Enabled,
        nextVisibility = View.GONE
    )

    data class Correct(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.Correct,
        skipVisibility = View.GONE,
        CheckUiState.Invisible,
        nextVisibility = View.VISIBLE
    )

    data class Incorrect(private val shuffledWord: String) : Abstract(
        shuffledWord,
        InputUiState.Incorrect,
        skipVisibility = View.VISIBLE,
        CheckUiState.Disabled,
        nextVisibility = View.GONE
    )
}


