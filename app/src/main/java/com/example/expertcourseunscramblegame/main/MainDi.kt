package com.example.expertcourseunscramblegame.main

import com.example.expertcourseunscramblegame.di.AbstractProvideViewModel
import com.example.expertcourseunscramblegame.di.Core
import com.example.expertcourseunscramblegame.di.Module
import com.example.expertcourseunscramblegame.di.ProvideViewModel

class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel(): MainViewModel {
        return MainViewModel(core.indexCache)
    }
}

class ProvideMainViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, MainViewModel::class.java) {

    override fun module() = MainModule(core)
}