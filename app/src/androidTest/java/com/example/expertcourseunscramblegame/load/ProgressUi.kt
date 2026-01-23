package com.example.expertcourseunscramblegame.load

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.expertcourseunscramblegame.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ProgressUi(
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisible(
    onView(
        allOf(
            withId(R.id.progressBar),
            isAssignableFrom(ProgressBar::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
)
