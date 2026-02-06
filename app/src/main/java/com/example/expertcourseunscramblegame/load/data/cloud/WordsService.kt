package com.example.expertcourseunscramblegame.load.data.cloud

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsService {

    @GET("api")
    fun words(
        @Query("words") wordsCount: Int
    ): Call<WordsResponseCloud>
}