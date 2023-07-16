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
import com.example.muslim.R
import com.example.muslim.adapter.Adapter
import com.example.muslim.adapter.Adapter2
import com.example.muslim.adapter.Adapter3
import com.example.muslim.databinding.FragmentAzkarBinding
import com.example.muslim.model.SessionManager
import com.example.muslim.viewModel.AzkarViewModel
import kotlinx.coroutines.launch

class AzkarFragment : Fragment() {

    lateinit var viewModel : AzkarViewModel
    lateinit var binding : FragmentAzkarBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(AzkarViewModel::class.java)
        binding = FragmentAzkarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myAdapter = Adapter3()
        val CachedData = SessionManager(requireContext()).getCachedAthkar()
        Log.i("Abdo",CachedData.toString())
        if(CachedData.isNotEmpty()) {
            myAdapter.setList(CachedData)
        }
        else {
            viewModel.getAzkar(requireContext())
            Handler().postDelayed({
                lifecycleScope.launch {
                    viewModel.AzkarList.collect{ myAdapter.setList(it !! )}
                }
            },1000)
        }


        binding.recyler.adapter = myAdapter
    }

}