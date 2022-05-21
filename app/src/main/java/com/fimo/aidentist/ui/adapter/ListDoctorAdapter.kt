package com.fimo.aidentist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fimo.aidentist.R
import com.fimo.aidentist.data.Doctor

class ListDoctorAdapter(private val listDoctor: ArrayList<Doctor>) :
    RecyclerView.Adapter<ListDoctorAdapter.ListViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    inner class ListViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.itemPhoto)
        var name: TextView = itemView.findViewById(R.id.itemName)
        var category: TextView = itemView.findViewById(R.id.itemCategory)
        var rating: TextView = itemView.findViewById(R.id.itemRating)
        var schedule: TextView = itemView.findViewById(R.id.itemSchedule)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_doctor, parent, false)
        return ListViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, category, rating, schedule, avatar) = listDoctor[position]
        holder.name.text = name
        holder.category.text = category
        holder.rating.text = rating
        holder.schedule.text = schedule
        Glide.with(holder.itemView.context)
            .load(avatar)
            .circleCrop()
            .into(holder.imgPhoto)
    }

    override fun getItemCount(): Int = listDoctor.size
}