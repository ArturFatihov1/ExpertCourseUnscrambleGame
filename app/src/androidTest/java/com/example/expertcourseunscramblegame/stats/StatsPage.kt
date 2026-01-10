package com.example.expertcourseunscramblegame.stats

import android.view.View
import android.widget.FrameLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.expertcourseunscramblegame.R
import com.example.expertcourseunscramblegame.game.ButtonUi
import org.hamcrest.Matcher

class StatsPage(skips: Int, fails: Int, corrects: Int) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.statsLayout))
    private val containerTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(FrameLayout::class.java))


    private val statsUi = StatsUi(
        containerIdMatcher = containerIdMatcher,
        containerTypeMatcher = containerTypeMatcher,
        skips = skips, fails = fails, corrects = corrects
    )
    private val newGameUi = ButtonUi(
        R.id.newGameButton,
        "#FF00000",
        R.string.new_game,
        containerIdMatcher,
        containerTypeMatcher
    )

    fun assertInitialState() {
        statsUi.assertVisible()
    }

    fun clickNewGame() {
        newGameUi.click()
    }

}