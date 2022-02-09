package com.example.carfax.viewModels

import com.example.carfax.room.CarDao
import com.example.carfax.services.WebServices

class MainViewModel(private val webService: WebServices, private val carDao: CarDao) :
    BaseViewModel() {

    init {
        getCars()
    }

    val cars = carDao.getLiveAll()

    private fun getCars() {
        makeCall(webService.getCars()) {
            it?.cars?.let { it1 -> carDao.insertAll(it1) }
        }
    }


}