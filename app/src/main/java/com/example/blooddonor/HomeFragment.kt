package com.example.blooddonor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.blooddonor.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.donorInfoButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donorFragment)
        }

        binding.recipientButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_donorListFragment)
        }
        return binding.root
    }

}