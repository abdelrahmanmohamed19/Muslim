package com.example.muslim.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muslim.R
import com.example.muslim.databinding.QuranitemBinding
import com.example.muslim.data.remote.dto.SuraList

class AyatTextItemAdapter() : RecyclerView.Adapter<AyatTextItemAdapter.viewHolder>()  {

    private var myDataList = emptyList<SuraList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quranitem,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = myDataList[position]
        holder.binding.AyaNumber.text = currentItem.aya.toString()
        holder.binding.QuranText.text = currentItem.text.toString()
        holder.binding.TafserText.text = currentItem.translation.toString()
    }

    override fun getItemCount() = myDataList.size

    fun setList(list : List<SuraList>){
        this.myDataList = list
        notifyDataSetChanged()
    }


    inner class viewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem){
        val binding =QuranitemBinding.bind(viewItem)
    }


}