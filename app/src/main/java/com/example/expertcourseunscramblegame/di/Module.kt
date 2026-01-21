package com.example.expertcourseunscramblegame.di


interface Module<T : MyViewModel> {
    fun viewModel(): T
}