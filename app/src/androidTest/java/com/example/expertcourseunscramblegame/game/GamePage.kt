package com.example.expertcourseunscramblegame.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.expertcourseunscramblegame.R
import org.hamcrest.Matcher

class GamePage(word: String) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val containerTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val shuffledWordUi = ShuffledWordUi(
        text = word,
        containerIdMatcher = containerIdMatcher,
        containerTypeMatcher = containerTypeMatcher
    )
    private val inputUi = InputUi(
        containerIdMatcher = containerIdMatcher,
        containerTypeMatcher = containerTypeMatcher
    )

    private val skipUi = ButtonUi(
        id = R.id.skipButton,
        colorHex = "#5AFFF5",
        textResId = R.string.skip,
        containerIdMatcher = containerIdMatcher,
        containerTypeMatcher = containerTypeMatcher
    )
    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        textResId = R.string.next,
        colorHex = "#8A80AC",
        containerIdMatcher = containerIdMatcher,
        containerTypeMatcher = containerTypeMatcher
    )
    private val checkUi = CheckButtonUi(
        containerIdMatcher = containerIdMatcher,
        containerTypeMatcher = containerTypeMatcher
    )

    fun assertInitialState() {
        shuffledWordUi.assertTextVisible()
        inputUi.assertInitialState()
        skipUi.assertVisible()
        checkUi.assertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun addInput(text: String) {
        inputUi.addInput(text = text)
    }

    fun assertInSufficientState() {
        shuffledWordUi.assertTextVisible()
        inputUi.assertInSufficientState()
        skipUi.assertVisible()
        checkUi.assertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun assertSufficientState() {
        shuffledWordUi.assertTextVisible()
        inputUi.assertSufficientState()
        skipUi.assertVisible()
        checkUi.assertVisibleEnabled()
        nextUi.assertNotVisible()
    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertCorrectState() {
        shuffledWordUi.assertTextVisible()
        inputUi.assertCorrectState()
        skipUi.assertNotVisible()
        checkUi.assertNotVisible()
        nextUi.assertVisible()
    }

    fun clickNext() {
        nextUi.click()
    }

    fun clickSkip() {
        skipUi.click()
    }

    fun assertIncorrectState() {
        shuffledWordUi.assertTextVisible()
        inputUi.assertIncorrectState()
        skipUi.assertVisible()
        checkUi.assertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun removeInputLastLetter() {
        inputUi.removeInputLastLetter()
    }

}