package com.example.expertcourseunscramblegame

import android.app.Application
import android.content.Context

class UnscrambleApp : Application() {

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