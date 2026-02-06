package com.example.expertcourseunscramblegame.load.presentation

import com.example.expertcourseunscramblegame.di.ClearViewModel
import com.example.expertcourseunscramblegame.di.MyViewModel
import com.example.expertcourseunscramblegame.load.data.LoadRepository
import com.example.expertcourseunscramblegame.load.data.cloud.HandleError
import com.example.expertcourseunscramblegame.main.RunAsync


class LoadViewModel(
    private val repository: LoadRepository,
    observable: LoadUiObservable,
    runAsync: RunAsync,
    private val clearViewModel: ClearViewModel,
    private val handleError: HandleError<Int>
) : MyViewModel.Abstract<LoadUiState>(runAsync, observable) {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            runAsync({
                try {
                    repository.load()
                    clearViewModel.clear(LoadViewModel::class.java)
                    LoadUiState.Success
                } catch (e: Exception) {
                    val resource = handleError.handle(e)
                    LoadUiState.ErrorRes(resource)
                }
            }) {
                observable.postUiState(it)
            }
        }
    }
}