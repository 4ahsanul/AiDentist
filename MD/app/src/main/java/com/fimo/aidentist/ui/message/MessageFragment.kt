package com.fimo.aidentist.ui.message

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.FragmentHomeBinding
import com.fimo.aidentist.databinding.FragmentMessageBinding
import com.fimo.aidentist.ui.consultation.OnlineConsultationActivity
import com.fimo.aidentist.ui.doctor.DoctorActivity
import com.fimo.aidentist.ui.treatment.DailyTreatmentActivity

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.cardView.setOnClickListener{
            val intent = Intent (activity, OnlineConsultationActivity::class.java)
            activity?.startActivity(intent)
        }
        return view

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}