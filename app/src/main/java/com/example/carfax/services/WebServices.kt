package com.example.carfax.services

import com.example.carfax.model.CarsContainers
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    @GET("assignment.json")
    fun getCars(): Call<CarsContainers>
}