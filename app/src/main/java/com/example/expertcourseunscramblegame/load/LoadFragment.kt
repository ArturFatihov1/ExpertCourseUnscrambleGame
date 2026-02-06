package com.example.expertcourseunscramblegame.load

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expertcourseunscramblegame.databinding.FragmentLoadBinding
import com.example.expertcourseunscramblegame.di.ProvideViewModel
import com.example.expertcourseunscramblegame.game.NavigateToGame

class LoadFragment : AbstractFragment<LoadUiState, LoadViewModel>() {
    private var _binding: FragmentLoadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override val update: (LoadUiState) -> Unit = { uiState ->
        uiState.show(
            binding.errorTextView,
            binding.retryButton,
            binding.progressBar
        )
        uiState.navigate((requireActivity() as NavigateToGame))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            (requireActivity() as ProvideViewModel).makeViewModel(LoadViewModel::class.java)

        binding.retryButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.load(isFirstRun = savedInstanceState == null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}