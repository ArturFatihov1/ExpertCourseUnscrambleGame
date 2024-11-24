package com.example.expertcourseunscramblegame.game

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.expertcourseunscramblegame.ButtonColorMatcher
import com.example.expertcourseunscramblegame.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class CheckButtonUi(
    containerIdMatcher: Matcher<View>,
    containerTypeMatcher: Matcher<View>
) : AbstractButtonUi(
    interaction = onView(
        allOf(
            containerIdMatcher,
            containerTypeMatcher,
            withId(R.id.checkButton),
            withText(R.string.check),
            isAssignableFrom(AppCompatButton::class.java),
            ButtonColorMatcher("#5367B7")
        )
    )
) {
    fun assertVisibleDisabled() {
        interaction
            .check(matches(isDisplayed()))
            .check(matches(isNotEnabled()))
    }

    fun assertVisibleEnabled() {
        interaction
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))
    }

}
