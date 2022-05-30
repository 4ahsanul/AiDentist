package com.fimo.aidentist.ui.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.ItemMessageBinding

class MessageAdapter(private val currentUserName: String?) :RecyclerView.Adapter<MessageAdapter.ViewHolder>()  {


    private val userName = "Dr.Jokowi"


    private val avatar = R.drawable.avatar10

    private val timestamp = arrayOf(
        "10.22",
        "10.23",
    )

    private val message = arrayOf(
        "Hy",
        "Kamu lagi ngapain johnny",
    )

    inner class ViewHolder(private val binding: ItemMessageBinding) :RecyclerView.ViewHolder(binding.root){
            val fotoProfil = binding.ivMessenger
            val namaProfil = binding.tvMessenger
            val pesan = binding.tvMessage
            val time = binding.tvTimestamp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_message, parent, false)
        val binding = ItemMessageBinding.bind(view)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(avatar)
            .circleCrop()
            .into(holder.fotoProfil)

        holder.namaProfil.text = userName
        holder.pesan.text = message[position]
        holder.time.text = timestamp[position]
    }

    override fun getItemCount(): Int = message.size


    private fun setTextColor(userName: String?, textView: TextView) {
        if (currentUserName == userName && userName != null) {
            textView.setBackgroundResource(R.drawable.rounded_message_blue)
        } else {
            textView.setBackgroundResource(R.drawable.rounded_message_cornflower)
        }
    }
}