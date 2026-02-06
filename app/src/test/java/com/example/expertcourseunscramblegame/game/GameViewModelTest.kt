package com.example.expertcourseunscramblegame.game

import com.example.expertcourseunscramblegame.game.data.GameRepository
import com.example.expertcourseunscramblegame.game.presentation.GameObservable
import com.example.expertcourseunscramblegame.game.presentation.GameUiState
import com.example.expertcourseunscramblegame.game.presentation.GameViewModel
import com.example.expertcourseunscramblegame.load.FakeRunAsyncImmediate
import com.example.expertcourseunscramblegame.load.FakeUiObservable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GameViewModelTest {

    private lateinit var observable: FakeGameObservable
    private lateinit var viewModel: GameViewModel
    private lateinit var runAsync: FakeRunAsyncImmediate
    private lateinit var repository: FakeRepository
    private lateinit var clearViewModel: FakeClearViewModel

    @Before
    fun setup() {
        clearViewModel = FakeClearViewModel()
        observable = FakeGameObservable.Base()
        runAsync = FakeRunAsyncImmediate()
        repository = FakeRepository()
        viewModel = GameViewModel(
            repository = repository,
            runAsync = runAsync,
            observable = observable,
            clearViewModel = clearViewModel
        )
    }

    /**
     * UGTC-01
     */
    @Test
    fun caseNumber1() {
        viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.handleUserInput(text = "1")
        expected = GameUiState.Insufficient
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.handleUserInput(text = "1f")
        expected = GameUiState.Sufficient
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.check(text = "1f")
        expected = GameUiState.Correct
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.next()
        expected = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(expected, observable.postUiStateCalledList.last())
    }

    /**
     * UGTC-02
     */
    @Test
    fun caseNumber2() {
        /*
        open app
        state is initial (some word)
         */
        viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(expected, observable.postUiStateCalledList.last())

        /*
        click skip
        state is initial (another word)
         */
        viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(expected, observable.postUiStateCalledList.last())

        /*
        input letter
        state is insufficient
        click skip
        state is initial (another word)
         */
        viewModel.handleUserInput(text = "1")
        expected = GameUiState.Insufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f3")
        assertEquals(expected, observable.postUiStateCalledList.last())

        /*
        input letters
        state is insufficient
        input more letters
        state is sufficient
        click skip
        state is initial (another word)
         */
        viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f4")
        assertEquals(expected, observable.postUiStateCalledList.last())

        /*
        input letters
        state is insufficient
        input more letters
        state is sufficient
        click check button
        state is incorrect
        click skip
        state is initial
         */
        viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.check(text = "f1")
        expected = GameUiState.Incorrect
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f5")
        assertEquals(expected, observable.postUiStateCalledList.last())

        /*
       input letters
       state is insufficient
       input more letters
       state is sufficient
       click check
       state is incorrect
       remove 1 letter
       state is insufficient
       input more letters
       state is sufficient
       input more letters
       state is insufficient
        */
        viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.check(text = "f1")
        expected = GameUiState.Incorrect
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.handleUserInput(text = "f")
        expected = GameUiState.Insufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.handleUserInput(text = "f1")
        expected = GameUiState.Sufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
        viewModel.handleUserInput(text = "f12")
        expected = GameUiState.Insufficient
        assertEquals(expected, observable.postUiStateCalledList.last())
    }

    @Test
    fun testLastWordNext() {
        repository.originalList = listOf("one", "two")

        viewModel.init(isFirstRun = true)
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "one".reversed())
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.handleUserInput(text = "one")
        expected = GameUiState.Sufficient
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.check(text = "one")
        expected = GameUiState.Correct
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.next()
        expected = GameUiState.Initial(shuffledWord = "two".reversed())
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.handleUserInput(text = "two")
        expected = GameUiState.Sufficient
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.check(text = "two")
        expected = GameUiState.Correct
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.next()
        expected = GameUiState.Finish
        assertEquals(expected, observable.postUiStateCalledList.last())

        assertEquals(GameViewModel::class.java, clearViewModel.clasz)
    }

    @Test
    fun testLastWordSkip() {
        repository.originalList = listOf("one", "two")

        viewModel.init(isFirstRun = true)

        var expected: GameUiState = GameUiState.Initial(shuffledWord = "one".reversed())
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.handleUserInput(text = "one")
        expected = GameUiState.Sufficient
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.check(text = "one")
        expected = GameUiState.Correct
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.next()
        expected = GameUiState.Initial(shuffledWord = "two".reversed())
        assertEquals(expected, observable.postUiStateCalledList.last())

        viewModel.skip()
        expected = GameUiState.Finish
        assertEquals(expected, observable.postUiStateCalledList.last())

        assertEquals(GameViewModel::class.java, clearViewModel.clasz)
    }
}

private class FakeRepository : GameRepository {

    var originalList: List<String> = listOf(
        "1f", "2f", "3f", "4f", "5f", "6f"
    )

    private val shuffledList
        get() = originalList.map { it.reversed() }

    private var index = 0

    override suspend fun shuffledWord(): String = shuffledList[index]

    override suspend fun isCorrect(text: String): Boolean {
        return originalList[index].equals(text, ignoreCase = true)
    }

    override fun skip() {
        next()
    }

    override fun next() {
        index++
        saveUserInput("")
    }

    override fun isLastWord(): Boolean {
        return index == originalList.size
    }

    private var input: String = ""

    override fun saveUserInput(value: String) {
        input = value
    }

    override fun userInput(): String {
        return input
    }
}


private interface FakeGameObservable : FakeUiObservable<GameUiState>, GameObservable {
    class Base : FakeUiObservable.Abstract<GameUiState>(), FakeGameObservable
}