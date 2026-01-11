package com.example.expertcourseunscramblegame.views.stats

interface StatsRepository {

    fun stats(): Triple<Int, Int, Int>

    fun clear()
}