package com.fimo.aidentist.ui.navigation.home

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.FragmentHomeBinding
import com.fimo.aidentist.ui.analisis.AnalisisFragment
import com.fimo.aidentist.ui.menu.doctor.DoctorActivity
import com.fimo.aidentist.ui.menu.treatment.DailyTreatmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(), DialogInterface.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var db = Firebase.firestore
    private lateinit var fAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        fAuth = Firebase.auth


        val docRef = db.collection("users").document(fAuth.currentUser?.uid.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data?.get("disease") != null) {
                    binding.infoData.displayedChild = 2
                    binding.infoData.stopFlipping()
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                    binding.infoData.displayedChild = 1
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
                binding.infoData.displayedChild = 1
            }


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
        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




    override fun onClick(dialog: DialogInterface?, which: Int) {

    }
}