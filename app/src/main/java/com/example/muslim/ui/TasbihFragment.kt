package com.example.muslim.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.muslim.R
import com.example.muslim.databinding.FragmentTasbihBinding

class TasbihFragment : Fragment() {

    lateinit var binding : FragmentTasbihBinding
    var counter = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTasbihBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCount.setOnClickListener {
            TasbihCount()
        }
    }

    fun TasbihCount(){

        if(counter<=33)
        {
            binding.buttonCount.text = "$counter/33"
            counter++
        }
        else{
            binding.buttonCount.text = "0/33"
            counter =1
        }
        }
    }