package com.fimo.aidentist.ui.menu.consultation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fimo.aidentist.databinding.ActivityOnlineConsultationBinding
import com.fimo.aidentist.ui.adapter.MessageAdapter

class OnlineConsultationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnlineConsultationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            super.onBackPressed()
        }

        binding.messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@OnlineConsultationActivity)
            adapter = MessageAdapter("Doctor Name")
        }
    }
}