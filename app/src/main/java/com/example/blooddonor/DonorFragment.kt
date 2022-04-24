package com.example.blooddonor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.blooddonor.databinding.FragmentDonorBinding
import com.example.blooddonor.dialogs.DateDialog
import com.example.blooddonor.entities.Donor
import com.example.blooddonor.models.DonorModel

class DonorFragment : Fragment() {
    private lateinit var binding : FragmentDonorBinding
    private val modelView : DonorModel by activityViewModels()
    private var bloodGroup = "AB+"
    private var gender = "Male"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonorBinding.inflate(inflater,container, false)
        bloodSelectSpinner()
        binding.genderRB.setOnCheckedChangeListener { radioGroup, i ->
            val rb : RadioButton = radioGroup.findViewById(i)
            gender = rb.text.toString()
        }

        binding.birthdayButton.setOnClickListener {
            DateDialog{
                binding.birthdayShowTV.text = it
            }.show(childFragmentManager,null)
        }

        binding.saveButton.setOnClickListener {
            val name = binding.donorName.text.toString()
            val age = binding.donorAge.text.toString()
            val phoneNumber = binding.donorPhoneNumber.text.toString()
            val birthday = binding.birthdayShowTV.text.toString()

            val newDonor = Donor(
                name = name,
                age = age,
                phone = phoneNumber,
                birthday = birthday,
                bloodGroup = bloodGroup,
                gender = gender
            )

            modelView.insertDonor(newDonor)
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun bloodSelectSpinner() {
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            bloodList
        )
        binding.bloodSpinner.adapter = adapter
        binding.bloodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                bloodGroup = p0?.getItemAtPosition(p2).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

}

val bloodList = listOf("AB+", "AB-", "A+", "A-","B+", "B-", "O+","O-")