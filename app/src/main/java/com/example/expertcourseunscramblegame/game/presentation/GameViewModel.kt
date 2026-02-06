package com.example.expertcourseunscramblegame.game.presentation

import com.example.expertcourseunscramblegame.di.ClearViewModel
import com.example.expertcourseunscramblegame.di.MyViewModel
import com.example.expertcourseunscramblegame.game.data.GameRepository
import com.example.expertcourseunscramblegame.main.RunAsync

class GameViewModel(
    runAsync: RunAsync,
    observable: GameObservable,
    private val repository: GameRepository,
    private val clearViewModel: ClearViewModel
) : MyViewModel.Abstract<GameUiState>(runAsync, observable) {

    private val updateUi: (GameUiState) -> Unit = {
        observable.postUiState(it)
    }

    fun next() {
        repository.next()
        return init()
    }

    fun check(text: String) {
        runAsync({
            if (repository.isCorrect(text))
                GameUiState.Correct
            else
                GameUiState.Incorrect
        }, updateUi)

    }

    fun skip() {
        repository.skip()
        return init()
    }

    fun handleUserInput(text: String) {
        runAsync({
            repository.saveUserInput(text)
            val shuffledWord = repository.shuffledWord()
            val isSufficient = text.length == shuffledWord.length
            if (isSufficient)
                GameUiState.Sufficient
            else
                GameUiState.Insufficient
        }, updateUi)
    }

    fun init(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            runAsync({
                if (repository.isLastWord()) {
                    clearViewModel.clear(GameViewModel::class.java)
                    GameUiState.Finish
                } else {
                    val shuffledWord = repository.shuffledWord()
                    GameUiState.Initial(shuffledWord, repository.userInput())
                }
            }, updateUi)
        }
    }
}