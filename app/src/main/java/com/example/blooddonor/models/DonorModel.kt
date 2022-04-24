package com.example.blooddonor.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.blooddonor.db.DonorDB
import com.example.blooddonor.entities.Donor
import com.example.blooddonor.repositories.DonorRepository
import kotlinx.coroutines.launch

class DonorModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository: DonorRepository
    init {
        val donorDao = DonorDB.getDB(application).getDonorDao()
        repository = DonorRepository(donorDao)
    }

    fun insertDonor(donor: Donor){
        viewModelScope.launch {
            repository.insertDonor(donor)
        }
    }

    fun getAllDonor() : LiveData<List<Donor>> = repository.getAllDonor()
}