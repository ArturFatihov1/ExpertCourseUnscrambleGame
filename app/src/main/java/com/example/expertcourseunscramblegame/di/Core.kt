package com.example.expertcourseunscramblegame.di

import android.content.Context
import androidx.room.Room
import com.example.expertcourseunscramblegame.R
import com.example.expertcourseunscramblegame.game.data.IntCache
import com.example.expertcourseunscramblegame.load.data.cache.WordsDao
import com.example.expertcourseunscramblegame.load.data.cache.WordsDatabase
import com.example.expertcourseunscramblegame.main.RunAsync
import com.example.expertcourseunscramblegame.stats.StatsCache

class Core(val context: Context, val clearViewModel: ClearViewModel) {

    val runUiTests = true

    val runAsync: RunAsync = RunAsync.Base()


    val sharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    val statsCache: StatsCache.All = StatsCache.Base(sharedPreferences)


    val indexCache = IntCache.Base(sharedPreferences, "indexKey", Int.MIN_VALUE)

    val wordsSize = 2

    val database by lazy {
        Room.databaseBuilder<WordsDatabase>(
            context,
            WordsDatabase::class.java,
            context.getString(R.string.app_name)
        ).build()
    }

    fun dao(): WordsDao = database.dao()
}