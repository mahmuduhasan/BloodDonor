package com.example.blooddonor.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.blooddonor.entities.Donor

@Dao
interface DonorDao {
    @Insert
    suspend fun insertDonor(donor: Donor)

    @Query("select * from tbl_donor")
    fun getAllDonor() : LiveData<List<Donor>>
}