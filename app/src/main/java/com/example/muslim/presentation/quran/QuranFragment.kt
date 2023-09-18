package com.example.muslim.presentation.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.muslim.presentation.adapter.SuraAdapter
import com.example.muslim.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {

    private var _binding : FragmentQuranBinding? = null
    val binding get() = _binding !!
    private lateinit var navController: NavController
    private val suraAdapter by lazy { SuraAdapter(navController) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentQuranBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.recyler.adapter = suraAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}