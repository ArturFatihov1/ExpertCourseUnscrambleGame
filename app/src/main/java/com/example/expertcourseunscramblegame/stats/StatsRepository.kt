package com.example.expertcourseunscramblegame.stats

interface StatsRepository {

    fun stats(): Triple<Int, Int, Int>

    fun clear()
}