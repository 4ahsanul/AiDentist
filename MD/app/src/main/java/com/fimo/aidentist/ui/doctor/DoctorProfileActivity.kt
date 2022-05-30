package com.fimo.aidentist.ui.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.ActivityDoctorProfileBinding
import com.fimo.aidentist.ui.adapter.DateAdapter
import com.fimo.aidentist.ui.adapter.MessageAdapter
import com.fimo.aidentist.ui.consultation.OfflineConsultationActivity
import com.fimo.aidentist.ui.consultation.OnlineConsultationActivity

class DoctorProfileActivity : AppCompatActivity() {


    private lateinit var binding : ActivityDoctorProfileBinding
    private lateinit var adapter : MessageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.onlineConsultation.setOnClickListener {
            val intent =  Intent(this ,OnlineConsultationActivity::class.java )
            startActivity(intent)
        }

        binding.offlineConsultation.setOnClickListener {
            val intent = Intent(this,OfflineConsultationActivity::class.java)
            startActivity(intent)
        }


    }





}