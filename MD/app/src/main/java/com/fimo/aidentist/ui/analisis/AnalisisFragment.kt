package com.fimo.aidentist.ui.analisis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fimo.aidentist.databinding.FragmentAnalisisBinding

class AnalisisFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentAnalisisBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAnalisisBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

}