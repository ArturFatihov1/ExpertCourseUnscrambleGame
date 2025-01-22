package com.example.expertcourseunscramblegame

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    /**
     * UGTC-01
     */

    @Test
    fun caseNumber1() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1")
        expected = GameUiState.Insufficient(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1f")
        expected = GameUiState.Sufficient(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.check(text = "1f")
        expected = GameUiState.Correct(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(expected, actual)
    }

    /**
     * UGTC-02
     */

    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1")
        expected = GameUiState.Insufficient(shuffledWord = "f2")
        assertEquals(expected, actual)
        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient(shuffledWord = "f3")
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient(shuffledWord = "f3")
        assertEquals(expected, actual)
        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient(shuffledWord = "f4")
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient(shuffledWord = "f4")
        assertEquals(expected, actual)
        actual = viewModel.check(text = "f1")
        expected = GameUiState.Incorrect(shuffledWord = "f4")
        assertEquals(expected, actual)
        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f5")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient(shuffledWord = "f5")
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient(shuffledWord = "f5")
        assertEquals(expected, actual)
        actual = viewModel.check(text = "f1")
        expected = GameUiState.Incorrect(shuffledWord = "f4")
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient(shuffledWord = "f5")
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient(shuffledWord = "f5")
        assertEquals(expected, actual)
        actual = viewModel.handleUserInput(text = "f12")
        expected = GameUiState.Insufficient(shuffledWord = "f5")
        assertEquals(expected, actual)
    }
}

private class FakeRepository : GameRepository {

    private var originalList = listOf("1f", "2f", "3f", "4f", "5f", "6f")
    private var shuffledList = originalList.map { it.reversed() }
    private var index = 0

    override fun shuffledWord(): String = shuffledList[index]

    override fun originalWord(): String = originalList[index]

    override fun next() {
        index++
        if (index == originalList.size)
            index = 0
    }
}