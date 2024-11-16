package com.example.expertcourseunscramblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expertcourseunscramblegame.game.GamePage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup() {
        gamePage = GamePage(word = "animal".reversed())
    }

    /**
     * UGTC-01 (UnscrambleGame - first testcase)
     */

    @Test
    fun caseNumber1() {
        gamePage.assertInitialState()

        gamePage.addInput(text = "anima")
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertCorrectState()

        gamePage.clickNext()
        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()


    }

    /**
     * UGTC-02 (UnscrambleGame - second testcase)
     */

    @Test
    fun caseNumber2() {
        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "auto".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "aut")
        gamePage.assertInSufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "anecdote".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "anecdot")
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "alphabet".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "alphabt")
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "all".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "al")
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()

        gamePage.removeInputLastLetter()
        gamePage.assertInSufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
    }
}