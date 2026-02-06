package com.example.expertcourseunscramblegame.load.data.cloud

interface WordsCloudDataSource {

    suspend fun words(): List<String>

    class Base(
        private val wordsSize: Int,
        private val service: WordsService,
    ) : WordsCloudDataSource {

        override suspend fun words(): List<String> {
            val data = service.words(wordsSize).execute()
            return data.body()!!.words
        }
    }
}