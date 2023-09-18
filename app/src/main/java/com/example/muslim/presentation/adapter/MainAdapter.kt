package com.example.muslim.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muslim.R
import com.example.muslim.databinding.HadithitemBinding
import com.example.muslim.data.remote.dto.Item
import com.example.muslim.domain.model.IslamicZakr

class MainAdapter() : RecyclerView.Adapter<MainAdapter.viewHolder>()  {

    private var myDataList = emptyList<IslamicZakr>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hadithitem,parent,false)
        return viewHolder(view)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = myDataList[position]
        holder.binding.hadithText.text = currentItem.content.toString()
    }

    override fun getItemCount() = myDataList.size

    fun setList(list : List<IslamicZakr>){
        this.myDataList = list
        notifyDataSetChanged()
    }


    inner class viewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem){
       val binding =HadithitemBinding.bind(viewItem)
    }

}