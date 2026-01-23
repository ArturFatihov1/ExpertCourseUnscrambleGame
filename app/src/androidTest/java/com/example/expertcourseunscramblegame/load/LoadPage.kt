package com.example.expertcourseunscramblegame.load

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.expertcourseunscramblegame.R
import com.example.expertcourseunscramblegame.game.ButtonUi
import org.hamcrest.Matcher

class LoadPage {

    private val containerIdMatcher: Matcher<View> =
        ViewMatchers.withParent(withId(R.id.loadContainer))
    private val classTypeMatcher: Matcher<View> =
        ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java))

    private val progressUi = ProgressUi(
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )

    private val errorUi = ErrorUi(
        viewId = R.id.errorTextView,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )

    private val retryUi = ButtonUi(
        R.id.retryButton,
        "#A020F0",
        R.string.retry,
        containerIdMatcher = containerIdMatcher,
        containerTypeMatcher = classTypeMatcher
    )

    fun assertProgressState() {
        progressUi.assertVisible()
        errorUi.assertNotVisible()
        retryUi.assertNotVisible()
    }

    fun waitTillError() {
        errorUi.waitTillVisible()
    }

    fun assertErrorState() {
        progressUi.assertNotVisible()
        errorUi.assertVisible()
        retryUi.assertVisible()
    }

    fun clickRetry() {
        retryUi.click()
    }

    fun waitTillGone() {
        errorUi.waitTillDoesntExist()
    }

}