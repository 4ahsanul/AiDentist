package com.fimo.aidentist.ui.menu.consultation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fimo.aidentist.data.Doctor
import com.fimo.aidentist.databinding.ActivityOnlineConsultationBinding
import com.fimo.aidentist.ui.adapter.MessageAdapter
import com.fimo.aidentist.ui.menu.doctor.DoctorProfileActivity

class OnlineConsultationActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var nameDoctor: String

    private lateinit var binding : ActivityOnlineConsultationBinding
    private lateinit var adapter : MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataAvatar: ImageView = binding.doctorProfile
        val dataName: TextView = binding.doctorName

        val doctor = intent.getParcelableExtra(EXTRA_USER) as Doctor?
        val image = doctor?.avatar
        nameDoctor = doctor?.name.toString()
        Glide.with(this)
            .load(image)
            .circleCrop()
            .into(dataAvatar)
        dataName.text = nameDoctor

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, DoctorProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
            startActivity(intent)
            finish()
        }

        binding.messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@OnlineConsultationActivity)
            adapter = MessageAdapter("Hand Oko")
        }


    }
}