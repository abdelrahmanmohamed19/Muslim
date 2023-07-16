package com.example.muslim.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.muslim.R
import com.example.muslim.adapter.Adapter
import com.example.muslim.databinding.FragmentHadithBinding
import com.example.muslim.model.SessionManager
import com.example.muslim.viewModel.HadithViewModel
import kotlinx.coroutines.launch

class HadithFragment : Fragment() {

    lateinit var binding : FragmentHadithBinding
    lateinit var viewModel : HadithViewModel
    private val adapter = Adapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHadithBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(HadithViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val CachedData = SessionManager(requireContext()).getCachedHadith()
        if(CachedData.isNotEmpty()) {
            adapter.setList(CachedData)
        }
        else {
            viewModel.getHadith(requireContext())
            Handler().postDelayed({
                lifecycleScope.launch {
                    viewModel.Hadith.collect{
                        adapter.setList(it !!)
                    }
                }
            },1000)

        }
        binding.recyler.adapter = adapter
    }
}