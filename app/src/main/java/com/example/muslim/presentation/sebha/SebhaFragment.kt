package com.example.muslim.presentation.sebha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.muslim.R

class SebhaFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var composeView: ComposeView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        navController = findNavController()
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {
            SebhaScreen(
                navigateToHomeScreen = { navController.navigate(R.id.action_sebhaFragment_to_homeFragment)}
            )
        }
    }
}