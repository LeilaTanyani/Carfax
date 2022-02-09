package com.example.carfax.extensions

fun Double.formatPrice() =
    "\$%,.2f".format(this)