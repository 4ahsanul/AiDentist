package com.fimo.aidentist.ui.treatment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.ActivityDailyTreatmentBinding
import com.fimo.aidentist.databinding.ActivityMainBinding
import com.fimo.aidentist.ui.adapter.DateAdapter
import com.fimo.aidentist.ui.adapter.ListDoctorAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class DailyTreatmentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDailyTreatmentBinding
    private lateinit var adapter : DateAdapter
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyTreatmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DateAdapter()
        binding.rvDate.apply {
            layoutManager = LinearLayoutManager(this@DailyTreatmentActivity,RecyclerView.HORIZONTAL,false)
            adapter = DateAdapter()
        }
        updateProgress()


    }


    fun updateProgress() {
        binding.progressBar.progress = 10;
    }
}
