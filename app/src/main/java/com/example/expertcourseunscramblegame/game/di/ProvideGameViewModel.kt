package com.example.expertcourseunscramblegame.game.di

import com.example.expertcourseunscramblegame.di.AbstractProvideViewModel
import com.example.expertcourseunscramblegame.di.Core
import com.example.expertcourseunscramblegame.di.Module
import com.example.expertcourseunscramblegame.di.ProvideViewModel
import com.example.expertcourseunscramblegame.game.GameRepository
import com.example.expertcourseunscramblegame.game.GameViewModel
import com.example.expertcourseunscramblegame.game.IntCache
import com.example.expertcourseunscramblegame.game.ShuffleStrategy
import com.example.expertcourseunscramblegame.game.StringCache

class ProvideGameViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, GameViewModel::class.java) {

    override fun module(): Module<*> = GameModule(core)
}

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel() = GameViewModel(
        GameRepository.Base(
            core.statsCache,
            IntCache.Base(core.sharedPreferences, "indexKey", 0),
            StringCache.Base(core.sharedPreferences, "userInputKey", ""),
            ShuffleStrategy.Reverse()
        ),
        core.clearViewModel,
    )
}
