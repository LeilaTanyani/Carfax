package com.example.carfax.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.carfax.R
import com.example.carfax.databinding.ItemCarBinding
import com.example.carfax.model.Car
import com.squareup.picasso.Picasso


class CarListAdapter(val onCallButtonClicked: (Car) -> Unit, val itemClick: (Car) -> Unit) :
    ListAdapter<Car, CarListAdapter.Holder>(ItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListAdapter.Holder =
        Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_car,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(private val binding: ItemCarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.car = car
            Picasso.get().load(car.largeImage).placeholder(R.drawable.placeholder)
                .into(binding.carImage)
            binding.itemContainer.setOnClickListener { itemClick(car) }
            binding.callDealerBtn.setOnClickListener { onCallButtonClicked(car) }
        }
    }

    class ItemCallBack : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean =
            oldItem.price == newItem.price
    }

}