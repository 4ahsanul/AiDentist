package com.fimo.aidentist.ui.home

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.FragmentHomeBinding
import com.fimo.aidentist.ui.auth.LoginActivity
import com.fimo.aidentist.ui.doctor.DoctorActivity
import com.fimo.aidentist.ui.treatment.DailyTreatmentActivity

class HomeFragment : Fragment(), DialogInterface.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.dailyTreatment.setOnClickListener{
            val intent = Intent (activity, DailyTreatmentActivity::class.java)
            activity?.startActivity(intent)
        }

        binding.dentistAppointment.setOnClickListener{
            val intent = Intent (activity, DoctorActivity::class.java)
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