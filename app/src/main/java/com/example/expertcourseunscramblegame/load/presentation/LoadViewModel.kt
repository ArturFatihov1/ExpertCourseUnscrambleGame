package com.example.expertcourseunscramblegame.load.presentation

import com.example.expertcourseunscramblegame.R
import com.example.expertcourseunscramblegame.di.ClearViewModel
import com.example.expertcourseunscramblegame.di.MyViewModel
import com.example.expertcourseunscramblegame.load.RunAsync
import com.example.expertcourseunscramblegame.load.data.LoadRepository
import com.example.expertcourseunscramblegame.load.data.NoInternetConnectionException


class LoadViewModel(
    private val repository: LoadRepository,
    observable: LoadUiObservable,
    private val runAsync: RunAsync,
    private val clearViewModel: ClearViewModel
) : MyViewModel.Abstract<LoadUiState>(observable) {

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            runAsync.handleAsync(viewModelScope, {
                try {
                    repository.load()
                    clearViewModel.clear(LoadViewModel::class.java)
                    LoadUiState.Success
                } catch (e: Exception) {
                    when (e) {
                        is NoInternetConnectionException -> LoadUiState.ErrorRes()
                        else -> LoadUiState.ErrorRes(R.string.service_unavailable)
                    }
                }
            }) {
                observable.postUiState(it)
            }
        }
    }
}