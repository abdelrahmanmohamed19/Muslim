package com.example.muslim.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.muslim.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment: Fragment() {

    private lateinit var composeView: ComposeView
    private lateinit var navController : NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        navController = findNavController()
        return ComposeView(requireContext()).also{
            composeView = it
        }
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       composeView.setContent {
           SplashScreen()
       }
       lifecycleScope.launch {
           delay(2000)
           navController.navigate(R.id.action_splashFragment_to_homeFragment)
       }
  }
}