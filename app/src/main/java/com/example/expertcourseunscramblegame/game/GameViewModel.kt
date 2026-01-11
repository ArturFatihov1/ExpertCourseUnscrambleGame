package com.example.expertcourseunscramblegame.game

class GameViewModel(private val repository: GameRepository) {

    fun skip(): GameUiState {
        repository.skip()
        return init()
    }

    fun next(): GameUiState {
        repository.next()
        return init()
    }

    fun check(text: String): GameUiState {
        return if (repository.isCorrect(text))
            GameUiState.Correct
        else
            GameUiState.Incorrect
    }

    fun handleUserInput(text: String): GameUiState {
        repository.saveUserInput(text)
        val shuffledWord = repository.shuffledWord()
        val isSufficient = text.length == shuffledWord.length
        return if (isSufficient)
            GameUiState.Sufficient
        else
            GameUiState.Insufficient

    }

    fun init(isFirstRun: Boolean = true): GameUiState {
        return if (isFirstRun) {
            if (repository.isLastWord())
                GameUiState.Finish
            else {
                val shuffledWord = repository.shuffledWord()
                GameUiState.Initial(shuffledWord, repository.userInput())
            }
        } else
            GameUiState.Empty

    }
}
