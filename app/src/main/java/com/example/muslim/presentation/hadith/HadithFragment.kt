package com.example.muslim.presentation.hadith

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.muslim.presentation.adapter.MainAdapter
import com.example.muslim.databinding.FragmentHadithBinding
import com.example.muslim.data.remote.dto.Item
import com.example.muslim.domain.model.IslamicZakr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HadithFragment : Fragment() {

    private var _binding : FragmentHadithBinding? = null
    val binding get() = _binding !!
    private val viewModel by viewModels<HadithViewModel>()
    private val adapter = MainAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHadithBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyler.adapter = adapter

        viewModel.getHadith(requireContext())
        lifecycleScope.launch() {
           viewModel.hadith.collect{
               if (it.isNotEmpty()) {
                   delay(300)
                   binding.loadingProgressBar.visibility = View.GONE
               }
                adapter.setList(mapHadithToIslamicAzkar(it))
            }
        }

    }

    private fun mapHadithToIslamicAzkar(list: List<Item>): List<IslamicZakr> {
        val newList = mutableListOf<IslamicZakr>()
        list.forEach {
            newList.add(IslamicZakr(it.arab.toString()))
        }
        return newList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}