package com.example.muslim.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.compose.runtime.currentComposer
import androidx.recyclerview.widget.RecyclerView
import com.example.muslim.R
import com.example.muslim.databinding.HadithitemBinding
import com.example.muslim.databinding.QuranitemBinding
import com.example.muslim.model.data.Item
import com.example.muslim.model.data.SuraList

class Adapter2() : RecyclerView.Adapter<Adapter2.viewHolder>()  {

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