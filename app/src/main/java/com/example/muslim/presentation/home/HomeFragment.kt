package com.example.muslim.presentation.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.muslim.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var composeView: ComposeView
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        navController = findNavController()
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {
            HomeScreen(
                navigateToAzkarScreen = { navController.navigate(R.id.action_homeFragment_to_azkarSelectionFragment) },
                navigateToSebhaScreen = { navController.navigate(R.id.action_homeFragment_to_sebhaFragment) },
                navigateToAllahNamesScreen = { navController.navigate(R.id.action_homeFragment_to_allahNamesFragment) },
                navigateToQuranScreen =  { navController.navigate(R.id.action_homeFragment_to_quranFragment)},
                navigateToDuaScreen = {navController.navigate(R.id.action_homeFragment_to_duaFragment)}
            )
        }
    }
}