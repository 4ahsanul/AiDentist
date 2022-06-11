package com.fimo.aidentist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fimo.aidentist.R
import com.fimo.aidentist.data.model.DoctorModel

class ListDoctorAdapter(
    private val listDoctor: ArrayList<DoctorModel>
) : RecyclerView.Adapter<ListDoctorAdapter.ListViewHolder>() {

    private lateinit var onItemClickDetail: OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickDetail = onItemClickCallBack
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.itemPhoto)
        var dataName: TextView = itemView.findViewById(R.id.itemName)
        var dataCategory: TextView = itemView.findViewById(R.id.itemCategory)
        var dataRating: TextView = itemView.findViewById(R.id.itemRating)
        var dataSchedule: TextView = itemView.findViewById(R.id.itemSchedule)
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: DoctorModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_doctor, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, category, rating, schedule, avatar) = listDoctor[position]
        Glide.with(holder.itemView.context)
            .load(avatar)
            .into(holder.imgPhoto)
        with(holder) {
            dataName.text = name
            dataCategory.text = category
            dataRating.text = rating
            dataSchedule.text = schedule
            itemView.setOnClickListener { onItemClickDetail.onItemClicked(listDoctor[holder.absoluteAdapterPosition]) }
        }
    }

    override fun getItemCount(): Int = listDoctor.size
}