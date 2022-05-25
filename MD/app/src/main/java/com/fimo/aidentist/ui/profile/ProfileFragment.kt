package com.fimo.aidentist.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fimo.aidentist.MainActivity
import com.fimo.aidentist.databinding.FragmentProfileBinding
import com.fimo.aidentist.helper.Constant
import com.fimo.aidentist.helper.PreferenceHelper
import com.fimo.aidentist.ui.auth.LoginActivity

class ProfileFragment : Fragment() {
    private lateinit var sharedPref: PreferenceHelper
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.buttonLogout?.setOnClickListener {
            activity?.let {
                sharedPref.put(Constant.PREF_IS_LOGIN, false)
                val intent = Intent(it, LoginActivity::class.java)
                it.startActivity(intent)
                Toast.makeText(activity, "LOGOUT SUCCESS", Toast.LENGTH_SHORT).show()
//                sharedPref.clear()
//                sharedPref.clear()
//                activity?.finish()
            }
        }
    }
}