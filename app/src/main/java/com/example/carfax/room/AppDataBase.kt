package com.example.carfax.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carfax.model.Car

@Database(entities = [Car::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {
        fun init(context: Context) =
            // init DB
            Room.databaseBuilder(context, AppDataBase::class.java, "carfax")
                .fallbackToDestructiveMigration().build()
    }
}