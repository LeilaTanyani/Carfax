package com.example.carfax.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Car constructor(
    @PrimaryKey val vin: String,
    val smallImage: String?,
    val largeImage: String?,
    val year: Int?,
    val make: String?,
    val model: String?,
    val price: Double?,
    val mileage: Int?,
    val location: String?,
    val interiorColor: String?,
    val exteriorColor: String?,
    val driveType: String?,
    val transmission: String?,
    val engine: String?,
    val bodyStyle: String?,
    val fuel: String?,
    val dealerPhone: String?
) : Serializable

