package com.fimo.aidentist.ui.doctor

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fimo.aidentist.data.Doctor
import com.fimo.aidentist.databinding.ActivityDoctorProfileBinding
import com.fimo.aidentist.ui.adapter.MessageAdapter
import com.fimo.aidentist.ui.consultation.OfflineConsultationActivity
import com.fimo.aidentist.ui.consultation.OnlineConsultationActivity

class DoctorProfileActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var nameDoctor: String
    private lateinit var doctorRating: String

    private lateinit var binding: ActivityDoctorProfileBinding
    private lateinit var adapter: MessageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataAvatar: ImageView = binding.doctorProfile
        val dataName: TextView = binding.doctorName
        val dataRating: TextView = binding.rating

        val doctor = intent.getParcelableExtra(EXTRA_USER) as Doctor?
        val image = doctor?.avatar
        nameDoctor = doctor?.name.toString()
        doctorRating = doctor?.rating.toString()
        Glide.with(this)
            .load(image)
            .circleCrop()
            .into(dataAvatar)
        dataName.text = nameDoctor
        dataRating.text = doctorRating

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.onlineConsultation.setOnClickListener {
            val intent = Intent(this, OnlineConsultationActivity::class.java)
            startActivity(intent)
        }

        binding.offlineConsultation.setOnClickListener {
            val intent = Intent(this, OfflineConsultationActivity::class.java)
            startActivity(intent)
        }
    }
}