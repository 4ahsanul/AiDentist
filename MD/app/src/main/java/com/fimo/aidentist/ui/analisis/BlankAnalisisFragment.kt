package com.fimo.aidentist.ui.analisis

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.FragmentBlankAnalisisBinding
import com.fimo.aidentist.databinding.FragmentHomeBinding
import com.fimo.aidentist.ui.navigation.camera.CameraActivity
import com.fimo.aidentist.ui.navigation.camera.CameraResultActivity


class BlankAnalisisFragment : Fragment() {

    private var _binding: FragmentBlankAnalisisBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBlankAnalisisBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.buttonCamera.setOnClickListener {
            val intent = Intent(activity, CameraResultActivity::class.java)
            activity?.startActivity(intent)
        }

        return view
    }

}