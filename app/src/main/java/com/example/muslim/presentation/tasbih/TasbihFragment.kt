package com.example.muslim.presentation.tasbih

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.muslim.databinding.FragmentTasbihBinding

class TasbihFragment : Fragment() {

    private var _binding : FragmentTasbihBinding? = null
    val binding get() = _binding !!
    private var counter = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTasbihBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCount.setOnClickListener {
            tasbihCount()
        }
    }

    private fun tasbihCount() {
        if(counter<=33) {
            binding.buttonCount.text = "$counter/33"
            counter++
        }
        else {
            binding.buttonCount.text = "0/33"
            counter =1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =  null
    }

}