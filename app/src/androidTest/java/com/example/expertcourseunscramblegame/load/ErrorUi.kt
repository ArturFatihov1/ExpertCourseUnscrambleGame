package com.example.expertcourseunscramblegame.load

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.expertcourseunscramblegame.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ErrorUi(
    private val viewId: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisible(
    onView(
        allOf(
            withId(viewId),
            withText(R.string.no_internet_connection),
            isAssignableFrom(TextView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
) {

    fun waitTillVisible() =
        onView(isRoot()).perform(waitTillDisplayed(viewId, 4000))


    fun waitTillDoesntExist() = onView(isRoot()).perform(waitTillDoesntExist(viewId, 4000))


}
