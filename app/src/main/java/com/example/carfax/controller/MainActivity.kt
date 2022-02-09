package com.example.carfax.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carfax.R
import com.example.carfax.adapters.CarListAdapter
import com.example.carfax.databinding.ActivityMainBinding
import com.example.carfax.utilities.EXTRA_CAR
import com.example.carfax.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val adapter by lazy {
        CarListAdapter(onCallButtonClicked = {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${it.dealerPhone}")))
        }) { car ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_CAR, car)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.carListRecView.layoutManager = LinearLayoutManager(this)
        binding.carListRecView.adapter = adapter

        viewModel.cars.observe(this) {
            adapter.submitList(it)
        }

    }
}