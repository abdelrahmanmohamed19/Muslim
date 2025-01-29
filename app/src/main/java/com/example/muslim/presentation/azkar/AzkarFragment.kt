package com.example.muslim.presentation.azkar

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzkarFragment: Fragment() {

    private lateinit var composeView: ComposeView
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        navController = findNavController()
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {
            AzkarScreen(
                navigateToAzkarSelectionScreen = {navController.navigate(R.id.action_azkarFragment_to_azkarSelectionFragment)}
            )
        }
    }
}