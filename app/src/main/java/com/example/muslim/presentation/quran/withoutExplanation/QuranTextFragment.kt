package com.example.muslim.presentation.quran.withoutExplanation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.muslim.R
import com.example.muslim.presentation.quran.QuranViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranTextFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var composeView: ComposeView
    private val quranViewModel by viewModels<QuranViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        navController = findNavController()
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {
            QuranTextScreen(
                navigateToQuranScreen = {navController.navigate(R.id.action_quranTextFragment_to_quranFragment)},
                quranViewModel
            )
        }
    }
}