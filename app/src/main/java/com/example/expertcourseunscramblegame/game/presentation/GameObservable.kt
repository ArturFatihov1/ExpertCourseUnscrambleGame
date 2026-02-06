package com.example.expertcourseunscramblegame.game.presentation

import com.example.expertcourseunscramblegame.main.UiObservable

interface GameObservable : UiObservable<GameUiState> {
    class Base : UiObservable.Abstract<GameUiState>(), GameObservable
}