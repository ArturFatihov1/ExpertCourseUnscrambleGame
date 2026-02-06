package com.example.expertcourseunscramblegame.load.di

import com.example.expertcourseunscramblegame.di.AbstractProvideViewModel
import com.example.expertcourseunscramblegame.di.Core
import com.example.expertcourseunscramblegame.di.Module
import com.example.expertcourseunscramblegame.di.ProvideViewModel
import com.example.expertcourseunscramblegame.load.data.LoadRepository
import com.example.expertcourseunscramblegame.load.data.cache.WordsCacheDataSource
import com.example.expertcourseunscramblegame.load.data.cloud.HandleError
import com.example.expertcourseunscramblegame.load.data.cloud.WordsCloudDataSource
import com.example.expertcourseunscramblegame.load.data.cloud.WordsService
import com.example.expertcourseunscramblegame.load.presentation.LoadUiObservable
import com.example.expertcourseunscramblegame.load.presentation.LoadViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoadModule(
    private val core: Core
) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel {
        return LoadViewModel(
            if (core.runUiTests)
                LoadRepository.Fake(core.indexCache)
            else
                LoadRepository.Base(
                    WordsCloudDataSource.Base(
                        core.wordsSize,
                        Retrofit.Builder()
                            .baseUrl("https://ao0ixd.buildship.run")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(
                                OkHttpClient.Builder()
                                    .addInterceptor(HttpLoggingInterceptor().apply {
                                        level = HttpLoggingInterceptor.Level.BODY
                                    })
                                    .build()
                            )
                            .build()
                            .create(WordsService::class.java)
                    ),
                    WordsCacheDataSource.Base(
                        core.dao()
                    ),
                    core.indexCache,
                    HandleError.DataToDomain()
                ),
            LoadUiObservable.Base(),
            core.runAsync,
            core.clearViewModel,
            HandleError.DomainToUi()
        )
    }
}

class ProvideLoadViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, LoadViewModel::class.java) {

    override fun module() = LoadModule(core)
}