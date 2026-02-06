package com.example.expertcourseunscramblegame.main

import com.example.expertcourseunscramblegame.game.presentation.GameScreen
import com.example.expertcourseunscramblegame.game.presentation.NavigateToGame
import com.example.expertcourseunscramblegame.load.presentation.LoadScreen
import com.example.expertcourseunscramblegame.load.presentation.NavigateToLoad
import com.example.expertcourseunscramblegame.stats.NavigateToStats
import com.example.expertcourseunscramblegame.stats.StatsScreen

interface Navigation : NavigateToGame, NavigateToStats, NavigateToLoad {
    fun navigate(screen: Screen)

    override fun navigateToGame() = navigate(GameScreen)

    override fun navigateToStats() = navigate(StatsScreen)

    override fun navigateToLoad() = navigate(LoadScreen)
}