package com.example.blooddonor.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_donor")
data class Donor(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val name : String,
    val age : String,
    val phone : String,
    @ColumnInfo(name = "blood_group")
    val bloodGroup : String,
    val gender : String,
    val birthday : String
)
