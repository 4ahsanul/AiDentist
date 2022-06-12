package com.fimo.aidentist.ui.menu.treatment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fimo.aidentist.MainActivity
import com.fimo.aidentist.databinding.FragmentTreatmentBinding
import com.fimo.aidentist.ui.adapter.DateAdapter

class TreatmentFragment : Fragment() {
    private lateinit var adapter: DateAdapter
    private var _binding: FragmentTreatmentBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        binding.buttonBack.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            activity?.startActivity(intent)
        }
        binding.progressBar.progress = 100
        return view
    }
}