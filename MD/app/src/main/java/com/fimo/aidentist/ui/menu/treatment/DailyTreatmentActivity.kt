package com.fimo.aidentist.ui.menu.treatment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.MainActivity
import com.fimo.aidentist.databinding.ActivityDailyTreatmentBinding
import com.fimo.aidentist.ui.adapter.DateAdapter

class DailyTreatmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDailyTreatmentBinding
    private lateinit var adapter: DateAdapter
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyTreatmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DateAdapter()
        binding.rvDate.apply {
            layoutManager =
                LinearLayoutManager(this@DailyTreatmentActivity, RecyclerView.HORIZONTAL, false)
            adapter = DateAdapter()
        }
        updateProgress()

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    fun updateProgress() {
        binding.progressBar.progress = 100
    }
}
