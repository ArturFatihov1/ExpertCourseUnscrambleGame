package com.example.expertcourseunscramblegame

import com.example.expertcourseunscramblegame.di.ClearViewModel
import com.example.expertcourseunscramblegame.di.MyViewModel

class FakeClearViewModel : ClearViewModel {

    var clasz: Class<out MyViewModel> = FakeViewModel::class.java

    override fun clear(viewModelClass: Class<out MyViewModel>) {
        clasz = viewModelClass
    }
}

private class FakeViewModel : MyViewModel