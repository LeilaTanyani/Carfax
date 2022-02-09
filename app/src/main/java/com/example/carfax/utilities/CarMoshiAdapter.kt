package com.example.carfax.utilities

import com.example.carfax.model.Car
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class CarMoshiAdapter {
    @FromJson
    fun fromJson(json: CarJson): Car {
        json.apply {
            return Car(
                vin,
                images?.firstPhoto?.smallImage,
                images?.firstPhoto?.largeImage,
                year,
                make,
                model,
                currentPrice,
                mileage,
                "${dealer?.city}, ${dealer?.state}",
                interiorColor,
                exteriorColor,
                driveType,
                transmission,
                engine,
                bodyType,
                fuel,
                dealer?.phone
            )
        }
    }

    @JsonClass(generateAdapter = true)
    data class CarJson(
        val dealer: Dealer?,
        val images: Images?,
        val vin: String,
        val year: Int?,
        val make: String?,
        val model: String?,
        val mileage: Int?,
        val currentPrice: Double?,
        val exteriorColor: String?,
        val interiorColor: String?,
        val engine: String?,
        @Json(name = "drivetype") val driveType: String?,
        val transmission: String?,
        @Json(name = "bodytype") val bodyType: String?,
        val fuel: String?,
    )

    @JsonClass(generateAdapter = true)
    data class Dealer(val phone: String?, val city: String?, val state: String?)

    data class FirstPhoto(
        @Json(name = "small") val smallImage: String?,
        @Json(name = "large") val largeImage: String?
    )

    data class Images(val firstPhoto: FirstPhoto?)
}