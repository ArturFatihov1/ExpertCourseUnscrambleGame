package com.example.expertcourseunscramblegame.di

import android.content.Context
import com.example.expertcourseunscramblegame.R
import com.example.expertcourseunscramblegame.stats.StatsCache

class Core(val context: Context, val clearViewModel: ClearViewModel) {

    val sharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    val statsCache: StatsCache.All = StatsCache.Base(sharedPreferences)
}