package com.fimo.aidentist.ui.menu.doctor

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.MainActivity
import com.fimo.aidentist.R
import com.fimo.aidentist.data.model.DoctorModel
import com.fimo.aidentist.databinding.ActivityDoctorBinding
import com.fimo.aidentist.ui.adapter.ListDoctorAdapter

class DoctorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorBinding
    private lateinit var rvDoctors: RecyclerView

    //Inisialisasi Variable
    private lateinit var dataName: Array<String>
    private lateinit var dataCategory: Array<String>
    private lateinit var dataRating: Array<String>
    private lateinit var dataSchedule: Array<String>
    private lateinit var dataAvatar: TypedArray
    private var list: ArrayList<DoctorModel> = arrayListOf()
    private var listDoctorAdapter = ListDoctorAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvDoctors = binding.rvDoctors
        rvDoctors.setHasFixedSize(true)

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        addItem()
        showRecyclerList()
    }

    private fun showSelectedDoctor(data: DoctorModel) {
        val moveObjectWithIntent = Intent(this@DoctorActivity, DoctorProfileActivity::class.java)
        moveObjectWithIntent.putExtra(DoctorProfileActivity.EXTRA_USER, data)
        startActivity(moveObjectWithIntent)
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataCategory = resources.getStringArray(R.array.category)
        dataRating = resources.getStringArray(R.array.rating)
        dataSchedule = resources.getStringArray(R.array.schedule)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
    }

    private fun addItem(): ArrayList<DoctorModel> {
        prepare()
        for (i in dataName.indices) {
            val doctor = DoctorModel(
                dataName[i],
                dataCategory[i],
                dataRating[i],
                dataSchedule[i],
                dataAvatar.getResourceId(i, -1)
            )
            list.add(doctor)
        }
        dataAvatar.recycle()
        return list
    }

    private fun showRecyclerList() {
        rvDoctors.layoutManager = LinearLayoutManager(this)
        rvDoctors.adapter = listDoctorAdapter
        listDoctorAdapter.setOnItemClickCallback(object : ListDoctorAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: DoctorModel) = showSelectedDoctor(data)
        })
    }
}