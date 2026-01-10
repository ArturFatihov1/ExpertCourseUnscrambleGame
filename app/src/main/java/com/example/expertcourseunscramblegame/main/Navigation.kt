package com.example.expertcourseunscramblegame.main

import com.example.expertcourseunscramblegame.game.GameScreen
import com.example.expertcourseunscramblegame.game.NavigateToGame
import com.example.expertcourseunscramblegame.stats.NavigateToStats
import com.example.expertcourseunscramblegame.stats.StatsScreen

interface Navigation : NavigateToGame, NavigateToStats {
    fun navigate(screen: Screen)

    override fun navigateToGame() {
        navigate(GameScreen)
    }

    override fun navigateToStats() {
        navigate(StatsScreen)
    }
}