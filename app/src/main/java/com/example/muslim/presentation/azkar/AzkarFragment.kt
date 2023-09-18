package com.example.muslim.presentation.azkar

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.muslim.databinding.FragmentAzkarBinding
import com.example.muslim.data.SessionManager
import com.example.muslim.data.remote.dto.AzkarList
import com.example.muslim.domain.model.IslamicZakr
import com.example.muslim.presentation.adapter.MainAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AzkarFragment : Fragment() {

   private var _binding : FragmentAzkarBinding? = null
    val binding get() = _binding !!
    private val viewModel by viewModels<AzkarViewModel>()
    private val adapter by lazy { MainAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAzkarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyler.adapter = adapter

        viewModel.getAzkar(requireContext())
        lifecycleScope.launch{
            viewModel.azkarList.collect{
                if (it.isNotEmpty()) {
                    delay(300)
                    binding.loadingProgressBar.visibility = View.GONE
                }
                adapter.setList(mapAzkarToIslamicZakr(it))
            }
        }
    }

    private fun mapAzkarToIslamicZakr(list : List<AzkarList>) : List<IslamicZakr>{
        val newList = mutableListOf<IslamicZakr>()
        list.forEach {
            newList.add(IslamicZakr(it.content))
        }
        return newList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =  null
    }

}