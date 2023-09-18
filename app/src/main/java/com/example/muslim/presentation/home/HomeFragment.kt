package com.example.muslim.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.muslim.R
import com.example.muslim.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    val binding get() = _binding !!
    private lateinit var navController : NavController
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        viewModel.getPrayerTimes(binding.snackbar,requireContext())

        lifecycleScope.launch() {
               viewModel.prayerTimes.collect{
                   when (it.month) {
                       "" -> {
                           binding.loadingProgressBar.visibility = View.VISIBLE
                       }
                       "No Internet Connection" -> {
                           binding.loadingProgressBar.visibility = View.INVISIBLE
                           binding.Date.text = it.month
                           binding.monthname.text = it.date
                           binding.Fajr.text =it.fajr
                           binding.Dhuhr.text =it.dhuhr
                           binding.Asr.text =it.asar
                           binding.Maghrib.text =it.maghrib
                           binding.Isha.text =it.isha
                       }
                       else -> {
                           binding.loadingProgressBar.visibility = View.INVISIBLE
                           binding.monthname.text = it.month
                           binding.Date.text = it.date
                           binding.Fajr.text = "   Fajr \n${it?.fajr.toString()} "
                           binding.Dhuhr.text = "   Dhuhr\n${it?.dhuhr.toString()}"
                           binding.Asr.text = "     Asr\n${it?.asar.toString()}"
                           binding.Maghrib.text = " Maghrib\n${it?.maghrib.toString()}"
                           binding.Isha.text = "    Isha\n${it?.isha.toString()}"

                       }
                   }

               }
           }

        binding.tasbihButton.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_tasbihFragment)
        }

        binding.HadithButton.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_hadithFragment)
        }

        binding.AzkarButton.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_azkarFragment)
        }

        binding.QuranButton.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_quranFragment)
        }

        binding.qibla.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_qiblaFragment)
        }
    }
}