package com.example.carfax.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarsContainers(@Json(name = "listings") val cars: List<Car>)