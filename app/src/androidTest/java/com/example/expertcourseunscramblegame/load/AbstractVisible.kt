package com.example.expertcourseunscramblegame.load

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.CoreMatchers.not

abstract class AbstractVisible(private val viewInteraction: ViewInteraction) {
    fun assertVisible() {
        viewInteraction.check(matches(isDisplayed()))
    }

    fun assertNotVisible() {
        viewInteraction.check(matches(not(isDisplayed())))
    }
}