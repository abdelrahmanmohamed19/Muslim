package com.example.muslim.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.muslim.R
import com.example.muslim.Repositories.HadithRepositorey
import com.example.muslim.databinding.FragmentHomeBinding
import com.example.muslim.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    lateinit var navController : NavController
    lateinit var viewModel : HomeViewModel
    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        Handler().postDelayed({
            lifecycleScope.launch {
                viewModel.MyData.collect{
                    it.let {
                        binding.monthname.text = it?.month.toString()
                        binding.Date.text = it?.date.toString()
                        binding.Fajr.text = "   Fajr \n${it?.fajr.toString()} "
                        binding.Dhuhr.text = "   Dhuhr\n${it?.dhuhr.toString()}"
                        binding.Asr.text = "     Asr\n${it?.asar.toString()}"
                        binding.Maghrib.text = " Maghrib\n${it?.maghrib.toString()}"
                        binding.Isha.text = "    Isha\n${it?.isha.toString()}"
                    }
                }
            }
        },500)

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