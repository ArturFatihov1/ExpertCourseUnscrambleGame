package com.example.expertcourseunscramblegame.game.di

import com.example.expertcourseunscramblegame.di.AbstractProvideViewModel
import com.example.expertcourseunscramblegame.di.Core
import com.example.expertcourseunscramblegame.di.Module
import com.example.expertcourseunscramblegame.di.ProvideViewModel
import com.example.expertcourseunscramblegame.game.data.GameRepository
import com.example.expertcourseunscramblegame.game.data.IntCache
import com.example.expertcourseunscramblegame.game.data.ShuffleStrategy
import com.example.expertcourseunscramblegame.game.data.StringCache
import com.example.expertcourseunscramblegame.game.presentation.GameObservable
import com.example.expertcourseunscramblegame.game.presentation.GameViewModel
import com.example.expertcourseunscramblegame.load.data.cache.WordsCacheDataSource

class ProvideGameViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, GameViewModel::class.java) {

    override fun module(): Module<*> = GameModule(core)
}

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel() = GameViewModel(
        core.runAsync,
        GameObservable.Base(),
        if (core.runUiTests)
            GameRepository.Fake(
                core.statsCache,
                IntCache.Base(core.sharedPreferences, "indexKey", 0),
                StringCache.Base(core.sharedPreferences, "userInputKey", ""),
                ShuffleStrategy.Reverse()
            )
        else
            GameRepository.Base(
                core.wordsSize,
                WordsCacheDataSource.Base(core.dao()),
                core.statsCache,
                core.indexCache,
                StringCache.Base(core.sharedPreferences, "userInputKey", ""),
                ShuffleStrategy.Base()
            ),
        core.clearViewModel,
    )
}
