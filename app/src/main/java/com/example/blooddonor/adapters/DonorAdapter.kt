package com.example.blooddonor.adapters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.blooddonor.databinding.DonorViewBinding
import com.example.blooddonor.entities.Donor

class DonorAdapter(val connectCallback : (Intent) -> Unit) : ListAdapter<Donor, DonorAdapter.DonorViewHolder>(DonorDiffUtil()) {
    class DonorViewHolder(val binding : DonorViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun viewBind(donor: Donor){
            binding.donor = donor
        }
    }

    class DonorDiffUtil : DiffUtil.ItemCallback<Donor>() {
        override fun areItemsTheSame(oldItem: Donor, newItem: Donor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Donor, newItem: Donor): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorViewHolder {
        val binding = DonorViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DonorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DonorViewHolder, position: Int) {
        val donor = getItem(position)
        holder.viewBind(donor)
        holder.binding.callButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${donor.phone}")
                }
                connectCallback(intent)
            }catch (e: ActivityNotFoundException){
                Log.d("Error", "Message: Application Not Found!")
            }
        }
        holder.binding.messageButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    data = Uri.parse("smsto:${donor.phone}")  // This ensures only SMS apps respond
                    putExtra("sms_body", "${donor.bloodGroup} Needed")
                }
                connectCallback(intent)
            }catch (e: ActivityNotFoundException){
                Log.d("Error", "Message: Application Not Found!")
            }
        }
    }
}