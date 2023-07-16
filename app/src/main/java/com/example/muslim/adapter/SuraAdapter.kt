package com.example.muslim.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muslim.R
import com.example.muslim.databinding.QuranbuttonBinding
import com.example.muslim.model.data.Sura

class SuraAdapter(private val suras: List<Sura>,  private val clickListener: SuraClickListener) : RecyclerView.Adapter<SuraAdapter.SuraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quranbutton, parent, false)
        return SuraViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SuraViewHolder, position: Int) {
        val sura = suras[position]
        holder.binding.QuranButton.text = "${sura.number.toString()}- ${sura.nameEnglish.toString()}\n${sura.nameArabic.toString()}"
        holder.binding.QuranButton.setOnClickListener {
            clickListener.onSuraClick(sura.number)
        }

    }

    override fun getItemCount() = suras.size

    inner class SuraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = QuranbuttonBinding.bind(itemView)
    }

    interface SuraClickListener {
        fun onSuraClick(number: Int)
    }


}
