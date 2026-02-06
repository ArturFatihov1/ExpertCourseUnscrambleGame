package com.example.expertcourseunscramblegame.load.presentation

import com.example.expertcourseunscramblegame.load.UiObservable

interface LoadUiObservable : UiObservable<LoadUiState> {
    class Base : UiObservable.Abstract<LoadUiState>(), LoadUiObservable
}