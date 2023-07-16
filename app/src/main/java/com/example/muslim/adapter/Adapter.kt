package com.example.muslim.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muslim.R
import com.example.muslim.databinding.HadithitemBinding
import com.example.muslim.model.data.Item

class Adapter() : RecyclerView.Adapter<Adapter.viewHolder>()  {

    private var myDataList = emptyList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hadithitem,parent,false)
        return viewHolder(view)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val curentItem = myDataList[position]
        holder.binding.hadithText.text = curentItem.arab.toString()
    }

    override fun getItemCount() = myDataList.size

    fun setList(list : List<Item>){
        this.myDataList = list
        notifyDataSetChanged()
    }


    inner class viewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem){
       val binding =HadithitemBinding.bind(viewItem)
    }

}