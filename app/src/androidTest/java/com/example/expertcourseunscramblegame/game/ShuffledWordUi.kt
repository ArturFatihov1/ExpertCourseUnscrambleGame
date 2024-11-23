package com.example.expertcourseunscramblegame.game

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.expertcourseunscramblegame.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ShuffledWordUi(
    text: String,
    containerIdMatcher: Matcher<View>,
    containerTypeMatcher: Matcher<View>
) {

    private val interaction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerTypeMatcher,
            withId(R.id.shuffledWordTextView),
            withText(text),
            isAssignableFrom(TextView::class.java)
        )
    )

    fun assertTextVisible() {
        interaction.check(matches(isDisplayed()))
    }

}
