package com.example.expertcourseunscramblegame.load.data

import com.example.expertcourseunscramblegame.game.data.IntCache
import com.example.expertcourseunscramblegame.load.data.cache.WordsCacheDataSource
import com.example.expertcourseunscramblegame.load.data.cloud.HandleError
import com.example.expertcourseunscramblegame.load.data.cloud.WordsCloudDataSource
import kotlinx.coroutines.delay

interface LoadRepository {

    suspend fun load()

    class Base(
        private val cloud: WordsCloudDataSource,
        private val cache: WordsCacheDataSource.Save,
        private val indexCache: IntCache,
        private val handleError: HandleError<Exception>
    ) : LoadRepository {

        override suspend fun load() {
            try {
                val data = cloud.words()
                cache.save(data)
                indexCache.save(0)
            } catch (e: Exception) {
                throw handleError.handle(e)
            }
        }
    }

    class Fake(
        private val indexCache: IntCache,
    ) : LoadRepository {

        private var count = 0

        override suspend fun load() {
            delay(3000)
            if (count == 0) {
                count++
                throw NoInternetConnectionException()
            } else {
                indexCache.save(0)
            }
        }
    }
}

class NoInternetConnectionException : Exception()

class ServiceUnavailable : Exception()