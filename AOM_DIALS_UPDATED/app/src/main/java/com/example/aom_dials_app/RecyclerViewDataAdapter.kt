package com.example.aom_dials_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewDataAdapter(val context: Context, val articles:List<Orders>): RecyclerView.Adapter<RecyclerViewDataAdapter.statisticsViewHolder>() {

    class statisticsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleView: TextView = itemView.findViewById(R.id.titleView)
        var descriptionView:TextView = itemView.findViewById(R.id.descriptionView)
        var orderImageView: ImageView = itemView.findViewById(R.id.orderImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): statisticsViewHolder {
        //LayoutInflater converts R.layouts.statistics_xml to view which we can pass in statistics view holder

        val view = LayoutInflater.from(context).inflate(R.layout.data_items,parent,false)
        return statisticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: statisticsViewHolder, position: Int) {
        val currentItem = articles[position]
        holder.titleView.text= currentItem.title
        holder.descriptionView.text= currentItem.description
        Glide.with(context).load(currentItem.urlToImage).into(holder.orderImageView)
    }

    override fun getItemCount(): Int {
        return  articles.size
    }
}