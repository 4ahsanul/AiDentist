package com.fimo.aidentist.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.R
import com.fimo.aidentist.ui.doctor.DoctorProfileActivity

class ListDoctorFragmentAdapter : RecyclerView.Adapter<ListDoctorFragmentAdapter.ViewHolder>() {
    private val name = arrayOf(
        "Jake Wharton",
        "Amit Shekhar",
        "Romain Guy",
        "Chris Banes",
        "David",
        "Ravi Tamada",
        "Deny Prasetyo",
        "Budi Oktaviyan",
        "Hendi Santika",
        "Sidiq Permana",
        "Jenifer Law",
        "Prandu Wanata",
        "Jessicato"
    )

    private val category = arrayOf(
        "Endodontist",
        "Oral Surgeon",
        "Periodontist",
        "Category 4",
        "Category 5",
        "Category 6",
        "Category 7",
        "Category 8",
        "Category 9",
        "Category 10",
        "Category 11",
        "Category 12",
        "Category 13",
    )

    private val rating = arrayOf(
        "2.3",
        "4.4",
        "3.2",
        "5.0",
        "4.1",
        "1.8",
        "4.8",
        "1.1",
        "0.0",
        "2.9",
        "3.9",
        "4.0",
        "4.7",
    )

    private val schedule = arrayOf(
        "07.00am - 03.00am",
        "08.00am - 04.00am",
        "09.00am - 05.00am",
        "10.00am - 06.00am",
        "11.00am - 07.00am",
        "12.00am - 08.00am",
        "08.00am - 04.00am",
        "09.00am - 05.00am",
        "10.00am - 06.00am",
        "11.00am - 07.00am",
        "10.30.00am - 07.00am",
        "07.00am - 07.00am",
        "08.00am - 12.00am",
        "11.00am - 01.00am",
    )

    private val avatar = arrayOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar6,
        R.drawable.avatar7,
        R.drawable.avatar8,
        R.drawable.avatar9,
        R.drawable.avatar10,
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView
        var itemCategory: TextView
        var itemRating: TextView
        var itemSchedule: TextView
        var ItemPhoto: ImageView

        init {
            itemName = itemView.findViewById(R.id.itemName)
            itemCategory = itemView.findViewById(R.id.itemCategory)
            itemRating = itemView.findViewById(R.id.itemRating)
            itemSchedule = itemView.findViewById(R.id.itemSchedule)
            ItemPhoto = itemView.findViewById(R.id.itemPhoto)

            itemView.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, DoctorProfileActivity::class.java).apply {
                    putExtra("NUMBER", position)
                    putExtra("NAME", itemName.text)
                    putExtra("CATEGORY", itemCategory.text)
                    putExtra("RATING", itemCategory.text)
                    putExtra("SCHEDULE", itemSchedule.text)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_doctor, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.itemName.text = name[i]
        holder.itemCategory.text = category[i]
        holder.itemRating.text = rating[i]
        holder.itemSchedule.text = schedule[i]
    }

    override fun getItemCount(): Int {
        return name.size
    }
}