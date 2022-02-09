package com.example.carfax.koin

import com.example.carfax.room.AppDataBase
import com.example.carfax.services.WebServices
import com.example.carfax.utilities.CarMoshiAdapter
import com.example.carfax.viewModels.DetailsViewModel
import com.example.carfax.viewModels.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    // single instance of AppDataBase
    single { AppDataBase.init(androidContext()) }

    // single instance of Cars Dao
    single { get<AppDataBase>().carDao() }

    // single instance of Moshi
    single<Moshi> { Moshi.Builder().add(CarMoshiAdapter()).add(KotlinJsonAdapterFactory()).build() }

    // single instance of OkHttpClient
    single<OkHttpClient> { OkHttpClient.Builder().build() }

    // single instance of WebServices
    single<WebServices> {
        val retrofit =
            Retrofit.Builder().baseUrl("https://carfax-for-consumers.firebaseio.com/")
                .addConverterFactory(MoshiConverterFactory.create(get()).asLenient())
                .client(get())
                .build()

        retrofit.create(WebServices::class.java)
    }


    // MainViewModel ViewModel
    viewModel { MainViewModel(get(),get()) }

    // Details view model
    viewModel { DetailsViewModel() }
}