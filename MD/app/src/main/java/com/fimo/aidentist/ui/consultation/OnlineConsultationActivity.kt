package com.fimo.aidentist.ui.consultation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.ActivityDoctorProfileBinding
import com.fimo.aidentist.databinding.ActivityOnlineConsultationBinding
import com.fimo.aidentist.ui.adapter.MessageAdapter

class OnlineConsultationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnlineConsultationBinding
    private lateinit var adapter : MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@OnlineConsultationActivity)
            adapter = MessageAdapter("Hand Oko")
        }


    }
}