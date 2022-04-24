package com.example.blooddonor.repositories

import androidx.lifecycle.LiveData
import com.example.blooddonor.daos.DonorDao
import com.example.blooddonor.entities.Donor

class DonorRepository(val donorDao: DonorDao) {
    suspend fun insertDonor(donor: Donor){
        donorDao.insertDonor(donor)
    }

    fun getAllDonor() : LiveData<List<Donor>> = donorDao.getAllDonor()
}