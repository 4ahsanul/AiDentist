package com.fimo.aidentist.ui.doctor

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.R
import com.fimo.aidentist.data.Doctor
import com.fimo.aidentist.ui.adapter.ListDoctorAdapter

class DoctorActivity : AppCompatActivity() {
    private lateinit var rvDoctors: RecyclerView

    //Inisialisasi Variable
    private lateinit var dataName: Array<String>
    private lateinit var dataCategory: Array<String>
    private lateinit var dataRating: Array<String>
    private lateinit var dataSchedule: Array<String>
    private lateinit var dataAvatar: TypedArray
    private var list: ArrayList<Doctor> = arrayListOf()
    private var listDoctorAdapter = ListDoctorAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        rvDoctors = findViewById(R.id.rvDoctors)
        rvDoctors.setHasFixedSize(true)

        addItem()
        showRecyclerList()
    }

    private fun showSelectedDoctor(data: Doctor) {
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

    private fun addItem(): ArrayList<Doctor> {
        prepare()
        for (i in dataName.indices) {
            val doctor = Doctor(
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
        listDoctorAdapter.setOnItemClickCallback(object: ListDoctorAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Doctor) = showSelectedDoctor(data)
        })
    }
}