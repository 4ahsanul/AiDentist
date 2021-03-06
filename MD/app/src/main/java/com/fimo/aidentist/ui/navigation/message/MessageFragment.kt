package com.fimo.aidentist.ui.navigation.message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fimo.aidentist.databinding.FragmentMessageBinding
import com.fimo.aidentist.ui.menu.consultation.OnlineConsultationActivity

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.cardView.setOnClickListener {
            val intent = Intent(activity, OnlineConsultationActivity::class.java)
            activity?.startActivity(intent)
        }
        return view

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}