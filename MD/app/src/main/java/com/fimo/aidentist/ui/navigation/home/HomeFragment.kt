package com.fimo.aidentist.ui.navigation.home

import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import com.fimo.aidentist.R
import com.fimo.aidentist.data.local.UserPreference
import com.fimo.aidentist.databinding.FragmentAnalisisBinding
import com.fimo.aidentist.databinding.FragmentHomeBinding
import com.fimo.aidentist.ui.analisis.*
import com.fimo.aidentist.ui.menu.doctor.DoctorActivity
import com.fimo.aidentist.ui.menu.treatment.DailyTreatmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class HomeFragment : Fragment(), DialogInterface.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private var _binding2: FragmentAnalisisBinding? = null
    private val binding get() = _binding!!
    private lateinit var preference: UserPreference
    private var db = Firebase.firestore
    private lateinit var fAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        replaceFragment(ShimmerFragment())
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        fAuth = Firebase.auth

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val docRef = db.collection("users").document("user")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data?.get("disease") != null) {
                    checkDisease()

                } else {
                    Log.d(ContentValues.TAG, "No such document")
                    replaceFragment(BlankAnalisisFragment())
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
                replaceFragment(BlankAnalisisFragment())
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preference = UserPreference.getInstance(requireContext().dataStore)
    }


    private fun replaceFragment(fragment: Fragment) {
        val nav = parentFragmentManager
        val trans = nav.beginTransaction()
        trans.replace(R.id.infoData, fragment)
        trans.commit()
    }

    private fun checkDisease() {
        val docRef = db.collection("users").document("user")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data?.get("disease") != "null") {

                    when {
                        document.data?.get("disease") == "Healthy" -> {
                            replaceFragment(AnalisisFragment3())
                        }
                        document.data?.get("disease") == "Dental Discoloration" -> {
                            replaceFragment(AnalisisFragment2())
                        }
                        document.data?.get("disease") == "Periodontal Disease" -> {
                            replaceFragment(AnalisisFragment())
                        }
                    }

                } else {
                    Log.d(ContentValues.TAG, "ASU")

                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)

            }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {

    }
}