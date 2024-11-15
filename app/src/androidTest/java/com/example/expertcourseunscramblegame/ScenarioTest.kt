package com.example.expertcourseunscramblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {
    private lateinit var gameStatus: GameStatus

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        gameStatus = GameStatus(
            shuffledWord = "ShuffledWord",
            editText = "try 1"
        )
    }

    /**
     * UG-01 (UnscrambleGame - first testcase)
     */

    @Test
    fun caseNumber1() {
        gameStatus.stateIsInitialSomeWord()

        gameStatus.inputLetter()
        gameStatus.stateIsInsufficient()

        gameStatus.inputLetter()
        gameStatus.stateIsSufficient()

        gameStatus.clickCheck()
        gameStatus.stateIsCorrect()

        gameStatus.clickNext()
        gameStatus.stateIsInitialAnotherWord()

    }

    /**
     * UG-02 (UnscrambleGame - second testcase)
     */

    @Test
    fun caseNumber2() {
        gameStatus.stateIsInitialSomeWord()

        gameStatus.clickSkip()
        gameStatus.stateIsInitialAnotherWord()

        gameStatus.inputLetter()
        gameStatus.stateIsSufficient()

        gameStatus.clickSkip()
        gameStatus.stateIsInitialAnotherWord()

        gameStatus.inputLetter()
        gameStatus.stateIsInsufficient()

        gameStatus.inputLetter()
        gameStatus.stateIsSufficient()

        gameStatus.clickSkip()
        gameStatus.stateIsInitialAnotherWord()

        gameStatus.inputLetter()
        gameStatus.stateIsInsufficient()

        gameStatus.inputLetter()
        gameStatus.stateIsSufficient()

        gameStatus.clickCheck()
        gameStatus.stateIsIncorrect()

        gameStatus.clickSkip()
        gameStatus.stateIsInitialAnotherWord()

        gameStatus.inputLetter()
        gameStatus.stateIsSufficient()

        gameStatus.clickCheck()
        gameStatus.stateIsIncorrect()

        gameStatus.removeLetter()
        gameStatus.stateIsInsufficient()

        gameStatus.addLetters()
        gameStatus.stateIsSufficient()

        gameStatus.removeLetter()
        gameStatus.stateIsInsufficient()

        gameStatus.addLetters()
        gameStatus.stateSufficient()

        gameStatus.clickCheck()
        gameStatus.stateIsIncorrect()
    }
}