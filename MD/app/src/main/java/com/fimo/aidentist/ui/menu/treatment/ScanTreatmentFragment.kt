package com.fimo.aidentist.ui.menu.treatment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fimo.aidentist.databinding.FragmentScanTreatmentBinding
import com.fimo.aidentist.ui.navigation.camera.CameraResultActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ScanTreatmentFragment : Fragment() {
    private var _binding: FragmentScanTreatmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var fAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentScanTreatmentBinding.inflate(inflater, container, false)
        val view = binding.root
        fAuth = Firebase.auth

        binding.buttonCamera.setOnClickListener {
            val intent = Intent(activity, CameraResultActivity::class.java)
            activity?.startActivity(intent)
        }
        return view
    }
}