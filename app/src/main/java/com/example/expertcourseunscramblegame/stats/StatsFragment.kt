package com.example.expertcourseunscramblegame.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.expertcourseunscramblegame.databinding.FragmentStatsBinding
import com.example.expertcourseunscramblegame.di.ProvideViewModel
import com.example.expertcourseunscramblegame.load.presentation.NavigateToLoad
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
            (requireActivity() as ProvideViewModel).makeViewModel(StatsViewModel::class.java)

        binding.newGameButton.setOnClickListener {
            viewModel.clear()
            (requireActivity() as NavigateToLoad).navigateToLoad()
        }
        val state: StatsUiState = viewModel.init(savedInstanceState == null)
        binding.statsTextView.update(state)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
