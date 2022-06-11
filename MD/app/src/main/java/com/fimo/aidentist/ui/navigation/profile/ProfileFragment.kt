package com.fimo.aidentist.ui.navigation.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fimo.aidentist.MainActivity
import com.fimo.aidentist.data.local.UserPreference
import com.fimo.aidentist.data.model.UserViewModel
import com.fimo.aidentist.data.model.ViewModelFactory
import com.fimo.aidentist.databinding.FragmentProfileBinding
import com.fimo.aidentist.helper.Constant
import com.fimo.aidentist.helper.PreferenceHelper
import com.fimo.aidentist.ui.menu.auth.LoginActivity
import com.fimo.aidentist.ui.menu.treatment.DailyTreatmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var preference: UserPreference
    private val userViewModel: UserViewModel by viewModels { ViewModelFactory.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        binding.buttonLogout.setOnClickListener {
            userViewModel.logout(preference)
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preference = UserPreference.getInstance(requireContext().dataStore)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}