package com.fimo.aidentist.ui.menu.treatment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.R
import com.fimo.aidentist.databinding.FragmentAnalisisBinding
import com.fimo.aidentist.databinding.FragmentTreatmentBinding
import com.fimo.aidentist.ui.adapter.DateAdapter

class TreatmentFragment : Fragment() {
    private lateinit var adapter: DateAdapter
    private var _binding: FragmentTreatmentBinding? = null
    public val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = DateAdapter()
        binding.rvDate.apply {
            layoutManager = LinearLayoutManager(context,
                RecyclerView.HORIZONTAL, false
            )
            adapter = DateAdapter()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTreatmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }
}