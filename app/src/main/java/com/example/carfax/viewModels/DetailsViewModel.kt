package com.example.carfax.viewModels

import androidx.lifecycle.MutableLiveData
import com.example.carfax.model.Car

class DetailsViewModel : BaseViewModel() {

    var car = MutableLiveData<Car>()
}