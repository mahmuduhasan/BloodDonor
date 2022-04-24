package com.example.blooddonor.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blooddonor.daos.DonorDao
import com.example.blooddonor.entities.Donor

@Database(entities = [Donor::class], version = 1)
abstract class DonorDB : RoomDatabase() {
    abstract fun getDonorDao() : DonorDao

    companion object{
        private var db : DonorDB? = null
        fun getDB(context: Context) : DonorDB{
            if(db == null){
                db = Room.databaseBuilder(
                    context.applicationContext,
                    DonorDB::class.java,
                    "donorDB"
                ).build()
                return db!!
            }
            return db!!
        }
    }
}