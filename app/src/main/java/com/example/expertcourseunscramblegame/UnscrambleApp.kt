package com.example.expertcourseunscramblegame

import android.app.Application
import android.content.Context
import com.example.expertcourseunscramblegame.game.GameRepository
import com.example.expertcourseunscramblegame.game.GameViewModel
import com.example.expertcourseunscramblegame.game.IntCache
import com.example.expertcourseunscramblegame.game.ShuffleStrategy
import com.example.expertcourseunscramblegame.game.StringCache
import com.example.expertcourseunscramblegame.stats.StatsCache
import com.example.expertcourseunscramblegame.stats.StatsViewModel

class UnscrambleApp : Application() {

    lateinit var statsViewModel: StatsViewModel
    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("UnscrambleAppData", Context.MODE_PRIVATE)
        val statsCache: StatsCache.All = StatsCache.Base(sharedPreferences)
        viewModel = GameViewModel(
            GameRepository.Base(
                statsCache,
                IntCache.Base(sharedPreferences, "indexKey", 0),
                StringCache.Base(sharedPreferences, "userInputKey", ""),
                ShuffleStrategy.Reverse()
            )
        )
    }
}