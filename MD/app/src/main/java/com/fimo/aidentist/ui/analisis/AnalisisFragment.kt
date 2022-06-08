package com.fimo.aidentist.ui.analisis

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.FragmentAnalisisBinding
import com.fimo.aidentist.databinding.FragmentHomeBinding
import com.fimo.aidentist.ui.menu.doctor.DoctorActivity
import com.fimo.aidentist.ui.menu.treatment.DailyTreatmentActivity

class AnalisisFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentAnalisisBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnalisisBinding.inflate(inflater, container, false)
         val view = binding.root

        binding.dailyTreatment.setOnClickListener{
            val intent = Intent (activity, DailyTreatmentActivity::class.java)
            activity?.startActivity(intent)
        }

        binding.medicineReminder.setOnClickListener{

        }

        binding.dentistAppointment.setOnClickListener{
            val intent = Intent (activity, DoctorActivity::class.java)
            activity?.startActivity(intent)
        }
        return  view
    }

}