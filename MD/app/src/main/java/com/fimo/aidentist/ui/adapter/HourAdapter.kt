package com.fimo.aidentist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.R

class HourAdapter : RecyclerView.Adapter<HourAdapter.ViewHolder>() {

    private val jam = arrayOf(
        "08:00",
        "09:00",
        "10:00",
        "13:00",
        "15:00",
        "18:00",
        "19:00"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hourItem: TextView = view.findViewById(R.id.hour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_hour, parent, false)
        )

    override fun onBindViewHolder(holder: HourAdapter.ViewHolder, position: Int) {
        holder.hourItem.text = jam[position]
    }

    override fun getItemCount(): Int = jam.size

}
