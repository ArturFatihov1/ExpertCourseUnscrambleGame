package com.example.expertcourseunscramblegame.main

import com.example.expertcourseunscramblegame.di.MyViewModel
import com.example.expertcourseunscramblegame.game.data.IntCache
import com.example.expertcourseunscramblegame.game.presentation.GameScreen
import com.example.expertcourseunscramblegame.load.presentation.LoadScreen

class MainViewModel(
    private val intCache: IntCache
) : MyViewModel {

    fun screen(firstRun: Boolean): Screen =
        if (firstRun) {
            if (intCache.read() < 0)
                LoadScreen
            else
                GameScreen
        } else
            Screen.Empty

}