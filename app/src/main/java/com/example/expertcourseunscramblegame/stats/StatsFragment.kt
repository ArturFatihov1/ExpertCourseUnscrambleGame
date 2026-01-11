package com.example.expertcourseunscramblegame.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.expertcourseunscramblegame.UnscrambleApp
import com.example.expertcourseunscramblegame.databinding.FragmentStatsBinding
import com.example.expertcourseunscramblegame.game.NavigateToGame
import com.example.expertcourseunscramblegame.views.stats.StatsUiState

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: StatsViewModel =
            (requireActivity().application as UnscrambleApp).statsViewModel

        binding.newGameButton.setOnClickListener {
            viewModel.clear()
            (requireActivity() as NavigateToGame).navigateToGame()
        }
        val state: StatsUiState = viewModel.init(savedInstanceState == null)
        state.show(binding.statsTextView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
