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

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.check(text = "f1")
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

        actual = viewModel.handleUserInput(text = "f")
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

}