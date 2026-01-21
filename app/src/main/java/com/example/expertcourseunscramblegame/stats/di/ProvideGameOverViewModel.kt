package com.example.expertcourseunscramblegame.stats.di

import com.example.expertcourseunscramblegame.di.AbstractProvideViewModel
import com.example.expertcourseunscramblegame.di.Core
import com.example.expertcourseunscramblegame.di.Module
import com.example.expertcourseunscramblegame.di.ProvideViewModel
import com.example.expertcourseunscramblegame.stats.StatsRepository
import com.example.expertcourseunscramblegame.stats.StatsViewModel

class ProvideGameOverViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, StatsViewModel::class.java) {
    override fun module(): Module<*> = GameOverModule(core)
}

class GameOverModule(
    private val core: Core
) : Module<StatsViewModel> {

    override fun viewModel() =
        StatsViewModel(StatsRepository.Base(core.statsCache), core.clearViewModel)

}
