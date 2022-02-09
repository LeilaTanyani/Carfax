package com.example.carfax.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.carfax.R
import com.example.carfax.databinding.ActivityDetailBinding
import com.example.carfax.model.Car
import com.example.carfax.utilities.EXTRA_CAR
import com.example.carfax.viewModels.DetailsViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityDetailBinding>(
            this,
            R.layout.activity_detail
        )
    }

    private val detailViewModel by viewModel<DetailsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel
        detailViewModel.car.postValue(intent.getSerializableExtra(EXTRA_CAR) as? Car)


        detailViewModel.car.observe(this) {
            Picasso.get().load(it.largeImage).placeholder(R.drawable.placeholder)
                .into(binding.detailImage)
        }

        binding.detailCallDealerBtn.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${detailViewModel.car.value?.dealerPhone}")
                )
            )
        }

    }
}