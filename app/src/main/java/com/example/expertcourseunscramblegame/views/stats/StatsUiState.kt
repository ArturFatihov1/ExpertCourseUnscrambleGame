package com.example.expertcourseunscramblegame.views.stats

import java.io.Serializable

interface StatsUiState : Serializable {
    abstract fun show(statsTextView: UpdateStats)

    class Base(
        private val skips: Int,
        private val corrects: Int,
        private val fails: Int
    ) : StatsUiState {
        override fun show(statsTextView: UpdateStats) {
            statsTextView.update(skips, fails, corrects)
        }
    }

    class Empty : StatsUiState {
        override fun show(statsTextView: UpdateStats) = Unit

    }
}
