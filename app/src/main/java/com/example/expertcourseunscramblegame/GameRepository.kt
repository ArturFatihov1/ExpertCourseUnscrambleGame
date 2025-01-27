package com.example.expertcourseunscramblegame

interface GameRepository {

    fun shuffledWord(): String
    fun originalWord(): String
    fun next()

    class Base(
        private val shuffledStrategy: ShuffleStrategy = ShuffleStrategy.Base(),
        private val originalList: List<String> = listOf(
            "animal",
            "auto",
            "anecdote",
            "alphabet",
            "all",
            "bench",
            "birthday",
            "camera",
            "camping"
        )
    ) :
        GameRepository {

        private var shuffledList = originalList.map { it.reversed() }

        private var index = 0

        override fun shuffledWord(): String = shuffledList[index]

        override fun originalWord(): String = originalList[index]

        override fun next() {
            index++
            if (index == originalList.size)
                index = 0
        }

    }
}

interface ShuffleStrategy {
    fun shuffle(source: String): String

    class Base() : ShuffleStrategy {
        override fun shuffle(source: String): String {
            return source // todo shuffle letters
        }
    }

    class Reverse : ShuffleStrategy {
        override fun shuffle(source: String): String {
            return source.reversed()
        }

    }
}

