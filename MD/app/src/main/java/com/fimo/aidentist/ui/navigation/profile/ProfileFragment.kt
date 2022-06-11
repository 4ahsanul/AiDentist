package com.fimo.aidentist.ui.navigation.profile

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fimo.aidentist.databinding.FragmentProfileBinding
import com.fimo.aidentist.helper.Constant
import com.fimo.aidentist.helper.PreferenceHelper
import com.fimo.aidentist.ui.menu.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private lateinit var sharedPref: PreferenceHelper
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var fAuth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        sharedPref = PreferenceHelper(requireActivity())
        fAuth = Firebase.auth
        binding.buttonLogout.setOnClickListener {
            sharedPref.put(Constant.PREF_IS_LOGIN, false)
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(activity, "LOGOUT PREFERENCE SUCCESS", Toast.LENGTH_SHORT).show()
            sharedPref.clear()
            resetData()
            fAuth.signOut()

            activity?.finish()
        }
        return binding.root
    }


    private fun resetData() {

        db.collection("users").document("user")
            .update("disease",null,"confidence",null)
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "Berhasil Menyimpan Data")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }

    }

}