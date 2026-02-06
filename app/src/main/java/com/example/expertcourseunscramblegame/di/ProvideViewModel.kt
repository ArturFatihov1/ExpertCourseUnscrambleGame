package com.example.expertcourseunscramblegame.di

import com.example.expertcourseunscramblegame.game.di.ProvideGameViewModel
import com.example.expertcourseunscramblegame.load.di.ProvideLoadViewModel
import com.example.expertcourseunscramblegame.main.ProvideMainViewModel
import com.example.expertcourseunscramblegame.stats.di.ProvideGameOverViewModel


interface ProvideViewModel {
    fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T


    class Make(core: Core) : ProvideViewModel {
        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideLoadViewModel(core, chain)
            chain = ProvideGameOverViewModel(core, chain)
            chain = ProvideMainViewModel(core, chain)
        }

        override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T =
            chain.makeViewModel(clasz)
    }

    class Error : ProvideViewModel {
        override fun <T : MyViewModel> makeViewModel(clasz: Class<T>): T {
            throw IllegalStateException("unknown class $clasz")
        }
    }

}