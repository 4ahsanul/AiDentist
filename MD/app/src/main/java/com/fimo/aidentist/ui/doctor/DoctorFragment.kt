package com.fimo.aidentist.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.databinding.FragmentDoctorBinding
import com.fimo.aidentist.ui.adapter.ListDoctorAdapter

class DoctorFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?= null
    private var adapter: RecyclerView.Adapter<ListDoctorAdapter.ViewHolder>? = null
    private var _binding: FragmentDoctorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_doctor, container, false)
        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDoctor.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListDoctorAdapter()
        }
    }

}