package com.example.muslim.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.muslim.R
import com.example.muslim.adapter.SuraAdapter
import com.example.muslim.databinding.FragmentQuranBinding
import com.example.muslim.model.data.Sura
import com.example.muslim.model.data.suras
import com.example.muslim.viewModel.QuranViewModel
import kotlinx.coroutines.launch

class QuranFragment : Fragment(), SuraAdapter.SuraClickListener {

    lateinit var viewModel : QuranViewModel
    lateinit var binding : FragmentQuranBinding
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(QuranViewModel::class.java)
        binding = FragmentQuranBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sura: List<Sura> = suras
        val myAdapter = SuraAdapter(sura, this)
        binding.recyler.adapter = myAdapter

    }
    override fun onSuraClick(number: Int) {
        val bundle = Bundle().apply {
            putInt("suraNumber", number)
        }
        navController.navigate(R.id.action_quranFragment_to_quranTextFragment,bundle)
    }
}