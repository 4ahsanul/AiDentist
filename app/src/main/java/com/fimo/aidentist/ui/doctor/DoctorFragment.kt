package com.fimo.aidentist.ui.doctor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.R
import com.fimo.aidentist.data.Doctor
import com.fimo.aidentist.ui.adapter.ListDoctorAdapter
import com.fimo.aidentist.ui.camera.CameraActivity

class DoctorFragment : Fragment() {
    private lateinit var rvDoctor: RecyclerView
    private val list = ArrayList<Doctor>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor, container, false)

        rvDoctor = requireView().findViewById(R.id.rvDoctor)
        rvDoctor.setHasFixedSize(true)

        list.addAll(listDoctor)
        showRecyclerList()
    }

    private val listDoctor: ArrayList<Doctor>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataCategory = resources.getStringArray(R.array.category)
            val dataRating = resources.getStringArray(R.array.rating)
            val dataSchedule = resources.getStringArray(R.array.schedule)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)
            val listDoctor = ArrayList<Doctor>()
            for (i in dataName.indices) {
                val doctor = Doctor(
                    dataName[i],
                    dataCategory[i],
                    dataRating[i],
                    dataSchedule[i],
                    dataPhoto.getResourceId(i, -1)
                )
                listDoctor.add(doctor)
            }
            return listDoctor
        }

    private fun showRecyclerList() {
        //rvDoctor.layoutManager = LinearLayoutManager(this)
        val listDoctorAdapter = ListDoctorAdapter(list)
        rvDoctor.adapter = listDoctorAdapter
        listDoctorAdapter.setOnItemClickListener(object : ListDoctorAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(activity, CameraActivity::class.java)
                activity?.startActivity(intent)
                intent.putExtra("avatar", listDoctor[position].avatar)
                intent.putExtra(
                    "userdata",
                    listDoctor[position].name + "\n"
                            + listDoctor[position].category + "\n"
                            + listDoctor[position].category + "\n"
                            + listDoctor[position].schedule + "\n"
                )
                intent.putExtra("name", listDoctor[position].name)
                startActivity(intent)
            }
        })
    }

}