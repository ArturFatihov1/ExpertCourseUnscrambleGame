package com.example.expertcourseunscramblegame.stats

import com.example.expertcourseunscramblegame.views.stats.StatsUiState

class StatsViewModel(private val repository: StatsRepository) {

    fun init(isFirstRun: Boolean): StatsUiState {
        return if (isFirstRun) {
            val (skips, corrects, fails) = repository.stats()
            StatsUiState.Base(skips, corrects, fails)
        } else {
            StatsUiState.Empty
        }
    }

    fun clear() {
        repository.clear()
    }

}