package com.example.muslim.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.muslim.R
import com.example.muslim.databinding.QuranbuttonBinding
import com.example.muslim.data.remote.dto.suras
import com.example.muslim.presentation.quran.QuranFragmentDirections

class SuraAdapter(private val navController: NavController) : RecyclerView.Adapter<SuraAdapter.SuraViewHolder>() {

    private var suraList = suras

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quranbutton, parent, false)
        return SuraViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SuraViewHolder, position: Int) {
        val sura = suraList[position]
        holder.binding.QuranButton.text = "${sura.number} - ${sura.nameEnglish}\n${sura.nameArabic}"
        holder.itemView.setOnClickListener{
            navController.navigate(QuranFragmentDirections.actionQuranFragmentToQuranTextFragment(sura.number))
        }
    }

    override fun getItemCount() = suraList.size
    inner class SuraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = QuranbuttonBinding.bind(itemView)
    }
}
