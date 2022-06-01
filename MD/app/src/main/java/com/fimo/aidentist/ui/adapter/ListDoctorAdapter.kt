package com.fimo.aidentist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.data.Doctor
import com.fimo.aidentist.databinding.ItemRowDoctorBinding

class ListDoctorAdapter(
    private val listDoctor: ArrayList<Doctor>
) : RecyclerView.Adapter<ListDoctorAdapter.ListViewHolder>() {

    private lateinit var onItemClickDetails: OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickDetails = onItemClickCallBack
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Doctor)
    }

    class ListViewHolder(private val itemBinding: ItemRowDoctorBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(doctor: Doctor) {
            itemBinding.itemName.text = doctor.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding =
            ItemRowDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val doctor: Doctor = listDoctor[position]
        holder.bind(doctor)
    }

    override fun getItemCount(): Int {
        return listDoctor.size
    }
}