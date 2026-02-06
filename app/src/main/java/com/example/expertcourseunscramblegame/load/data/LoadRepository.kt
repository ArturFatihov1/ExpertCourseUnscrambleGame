package com.example.expertcourseunscramblegame.load.data

interface LoadRepository {
    suspend fun load()
}

class NoInternetConnectionException : Exception()

class ServiceUnavailable : Exception()