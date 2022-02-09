package com.example.carfax.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

open class BaseViewModel : ViewModel() {

    val error = MutableLiveData<String>()

    val isLoading = MutableLiveData<Boolean>()

    fun <T> makeCall(call: Call<T>, onSuccess: (T?) -> Unit) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = call.execute()
            if (response.isSuccessful) {
                Log.d("@LH", "${response.body()}")
                onSuccess(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
            isLoading.postValue(false)
        }
    }
}