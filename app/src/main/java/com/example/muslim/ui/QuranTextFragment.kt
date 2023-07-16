package com.example.muslim.ui

import android.os.Bundle
import android.os.Handler
import android.se.omapi.Session
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
import com.example.muslim.adapter.Adapter
import com.example.muslim.adapter.Adapter2
import com.example.muslim.adapter.SuraAdapter
import com.example.muslim.databinding.FragmentQuranTextBinding
import com.example.muslim.model.SessionManager
import com.example.muslim.viewModel.QuranViewModel
import kotlinx.coroutines.launch

class QuranTextFragment : Fragment(){

    lateinit var viewModel: QuranViewModel
    lateinit var binding: FragmentQuranTextBinding
    lateinit var navController: NavController
    val myAdapter = Adapter2()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(QuranViewModel::class.java)
        binding = FragmentQuranTextBinding.inflate(layoutInflater)
        navController=findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val suraNumber = arguments?.getInt("suraNumber")
        Log.i("Abdo",suraNumber.toString())
        viewModel.getQuran(suraNumber !!)

            Handler().postDelayed(
                {
                    lifecycleScope.launch {
                        viewModel.QuranList.collect {myAdapter.setList(it !!) }
                    }
                }
                ,1000)

        binding.recyler.adapter = myAdapter
    }
}