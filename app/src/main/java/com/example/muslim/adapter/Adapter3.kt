package com.example.muslim.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muslim.R
import com.example.muslim.databinding.HadithitemBinding
import com.example.muslim.model.data.AzkarList
import com.example.muslim.model.data.Item

class Adapter3 : RecyclerView.Adapter<Adapter3.viewHolder>()  {

        private var myDataList = emptyList<AzkarList>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.hadithitem,parent,false)
            return viewHolder(view)
        }

        override fun onBindViewHolder(holder: viewHolder, position: Int) {
            val curentItem = myDataList[position]
            holder.binding.hadithText.text = curentItem.content.toString()
        }

        override fun getItemCount() = myDataList.size

        fun setList(list : List<AzkarList>){
            this.myDataList = list
            notifyDataSetChanged()
        }


        inner class viewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem){
            val binding = HadithitemBinding.bind(viewItem)
        }


    }
