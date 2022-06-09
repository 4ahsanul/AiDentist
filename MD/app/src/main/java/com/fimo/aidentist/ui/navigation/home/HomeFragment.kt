package com.fimo.aidentist.ui.navigation.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fimo.aidentist.databinding.FragmentHomeBinding
import com.fimo.aidentist.ui.menu.doctor.DoctorActivity
import com.fimo.aidentist.ui.menu.treatment.DailyTreatmentActivity

class HomeFragment : Fragment(), DialogInterface.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.dailyTreatment.setOnClickListener {
            val intent = Intent(activity, DailyTreatmentActivity::class.java)
            activity?.startActivity(intent)
        }

        binding.medicineReminder.setOnClickListener {

        }

        binding.dentistAppointment.setOnClickListener {
            val intent = Intent(activity, DoctorActivity::class.java)
            activity?.startActivity(intent)
        }
        return view

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {

    }
}