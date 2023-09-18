package com.example.muslim.presentation.quran

import NetworkUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muslim.databinding.FragmentQuranTextBinding
import com.example.muslim.presentation.adapter.AyatTextItemAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuranTextFragment : Fragment(){

    private var _binding : FragmentQuranTextBinding? = null
    val binding get() = _binding !!
    private val viewModel by viewModels <QuranViewModel> ()
    private lateinit var navController: NavController
    private val adapter by lazy { AyatTextItemAdapter() }
    private val args by navArgs<QuranTextFragmentArgs> ()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentQuranTextBinding.inflate(layoutInflater)
        navController=findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.name
        binding.recyler.adapter = adapter
        binding.loadingProgressBar.visibility = View.VISIBLE


        viewModel.getQuran(id,requireContext(),binding.coordinatorLayout)
        lifecycleScope.launch {
          viewModel.quranList.collect{
              if (!NetworkUtils.isInternetConnected() && it.isEmpty()) {
                  delay(350)
              }

              if (it.isNotEmpty()) {
                  delay(300)
                  binding.loadingProgressBar.visibility = View.GONE
              }
              adapter.setList(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}