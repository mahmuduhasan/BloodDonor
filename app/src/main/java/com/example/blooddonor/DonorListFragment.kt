package com.example.blooddonor

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blooddonor.adapters.DonorAdapter
import com.example.blooddonor.databinding.FragmentDonorListBinding
import com.example.blooddonor.models.DonorModel

class DonorListFragment : Fragment() {
    private lateinit var binding : FragmentDonorListBinding
    private val modelView : DonorModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonorListBinding.inflate(inflater,container,false)
        val adapter = DonorAdapter(::onConnectButtonClick)
        binding.donorRecycleView.layoutManager = LinearLayoutManager(activity)
        binding.donorRecycleView.adapter = adapter
        modelView.getAllDonor().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        return binding.root
    }
    private fun onConnectButtonClick(intent: Intent){
        startActivity(intent)
    }
}