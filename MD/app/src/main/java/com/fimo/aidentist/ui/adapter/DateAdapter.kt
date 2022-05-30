package com.fimo.aidentist.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.R
import com.fimo.aidentist.ui.camera.CameraActivity
import java.time.LocalDateTime

class DateAdapter : RecyclerView.Adapter<DateAdapter.ViewHolder>(){

    private val hari = arrayOf(
        "Sun",
        "Mon",
        "Thu",
        "Wed",
        "Thu",
        "Fri",
        "Sat"
    )

    private val tanggal = arrayOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7"
    )

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val dateItem:TextView = view.findViewById(R.id.hari)
        val tanggalItem:TextView = view.findViewById(R.id.tanggal)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_date,parent,false))


    override fun onBindViewHolder(holder: DateAdapter.ViewHolder, position: Int) {
        holder.dateItem.text = hari[position]
        holder.tanggalItem.text = tanggal[position]
    }

    override fun getItemCount(): Int = hari.size

}
