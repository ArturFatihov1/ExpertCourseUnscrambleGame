package com.example.expertcourseunscramblegame.load.presentation

import com.example.expertcourseunscramblegame.main.UiObservable

interface LoadUiObservable : UiObservable<LoadUiState> {
    class Base : UiObservable.Abstract<LoadUiState>(), LoadUiObservable
}