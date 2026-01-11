package com.example.expertcourseunscramblegame

import android.app.Application
import android.content.Context
import com.example.expertcourseunscramblegame.game.GameRepository
import com.example.expertcourseunscramblegame.game.GameViewModel
import com.example.expertcourseunscramblegame.game.IntCashes
import com.example.expertcourseunscramblegame.game.ShuffleStrategy
import com.example.expertcourseunscramblegame.game.StringCache

class UnscrambleApp : Application() {

    lateinit var statsViewModel: StatsViewModel
    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("UnscrambleAppData", Context.MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                IntCashes.Base(sharedPreferences, "indexKey", 0),
                StringCache.Base(sharedPreferences, "userInputKey", ""),
                ShuffleStrategy.Reverse()
            )
        )
    }
}