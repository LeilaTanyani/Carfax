package com.example.carfax.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.carfax.model.Car

@Dao
interface CarDao {

    @Query("SELECT * FROM Car")
    fun getLiveAll(): LiveData<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: Car)

    fun insertAll(cars: List<Car>) {
        cars.forEach { insert(it) }
    }
}