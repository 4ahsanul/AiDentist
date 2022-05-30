package com.fimo.aidentist.ui.consultation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.ActivityOfflineConsultationBinding
import com.fimo.aidentist.ui.adapter.DateAdapter
import com.fimo.aidentist.ui.adapter.HourAdapter

class OfflineConsultationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOfflineConsultationBinding
    private lateinit var adapter : DateAdapter
    private lateinit var adapter2 : HourAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfflineConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvDate.apply {
            layoutManager = LinearLayoutManager(this@OfflineConsultationActivity,
                RecyclerView.HORIZONTAL,false)
            adapter = DateAdapter()
        }

        binding.rvTime.apply {
            layoutManager = LinearLayoutManager(this@OfflineConsultationActivity, RecyclerView.HORIZONTAL,false)
            adapter = HourAdapter()
        }


    }
}