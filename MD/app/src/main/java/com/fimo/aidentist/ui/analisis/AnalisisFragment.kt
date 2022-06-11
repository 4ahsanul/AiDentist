package com.fimo.aidentist.ui.analisis

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.FragmentAnalisisBinding
import com.fimo.aidentist.databinding.FragmentHomeBinding
import com.fimo.aidentist.ui.menu.doctor.DoctorActivity
import com.fimo.aidentist.ui.menu.treatment.DailyTreatmentActivity
import com.fimo.aidentist.utils.loadImage
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AnalisisFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentAnalisisBinding? = null
    public val binding get() = _binding!!
    private var db = Firebase.firestore



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

        return  view
    }








}