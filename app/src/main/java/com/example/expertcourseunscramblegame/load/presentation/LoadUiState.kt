package com.example.expertcourseunscramblegame.load.presentation

import com.example.expertcourseunscramblegame.R
import com.example.expertcourseunscramblegame.game.presentation.NavigateToGame
import com.example.expertcourseunscramblegame.views.error.ErrorUiState
import com.example.expertcourseunscramblegame.views.error.UpdateError
import com.example.expertcourseunscramblegame.views.visibilitybutton.UpdateVisibility
import com.example.expertcourseunscramblegame.views.visibilitybutton.VisibilityUiState

interface LoadUiState {
    fun show(
        errorTextView: UpdateError,
        retryButton: UpdateVisibility,
        progressBar: UpdateVisibility
    )

    fun navigate(game: NavigateToGame) = Unit

    abstract class Abstract(
        private val errorUiState: ErrorUiState,
        private val retryUiState: VisibilityUiState,
        private val progressUiState: VisibilityUiState
    ) : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retryButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            errorTextView.update(errorUiState)
            retryButton.update(retryUiState)
            progressBar.update(progressUiState)
        }
    }

    object Progress : Abstract(
        ErrorUiState.Hide,
        VisibilityUiState.Gone,
        VisibilityUiState.Visible
    )

    object Success : Abstract(
        ErrorUiState.Hide,
        VisibilityUiState.Gone,
        VisibilityUiState.Gone
    ) {
        override fun navigate(game: NavigateToGame) = game.navigateToGame()
    }

    data class ErrorRes(val messageId: Int = R.string.no_internet_connection) : Abstract(
        ErrorUiState.ShowRes(messageId),
        VisibilityUiState.Visible,
        VisibilityUiState.Gone,
    )

    data class Error(private val message: String) : Abstract(
        ErrorUiState.Show(message),
        VisibilityUiState.Visible,
        VisibilityUiState.Gone,
    )
}