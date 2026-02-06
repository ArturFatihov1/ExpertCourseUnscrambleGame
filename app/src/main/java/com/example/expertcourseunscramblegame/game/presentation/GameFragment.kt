package com.example.expertcourseunscramblegame.game.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expertcourseunscramblegame.databinding.FragmentGameBinding
import com.example.expertcourseunscramblegame.di.ProvideViewModel
import com.example.expertcourseunscramblegame.main.AbstractFragment
import com.example.expertcourseunscramblegame.stats.NavigateToStats


class GameFragment : AbstractFragment.Async<GameUiState, GameViewModel, FragmentGameBinding>() {

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            viewModel.handleUserInput(text = s.toString())
        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentGameBinding.inflate(inflater, container, false)

    override val update: (GameUiState) -> Unit = { uiState ->
        uiState.update(
            binding.shuffledWordTextView,
            binding.inputView,
            binding.skipButton,
            binding.checkButton,
            binding.nextButton
        )
        uiState.navigate(requireActivity() as NavigateToStats)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            (requireActivity() as ProvideViewModel).makeViewModel(GameViewModel::class.java)

        binding.nextButton.setOnClickListener {
            viewModel.next()
        }

        binding.checkButton.setOnClickListener {
            viewModel.check(text = binding.inputView.text())
        }

        binding.skipButton.setOnClickListener {
            viewModel.skip()
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        binding.inputView.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputView.removeTextChangedListener(textWatcher)
    }
}