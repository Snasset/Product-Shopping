package com.dheril.productshopping

import android.os.ParcelFileDescriptor
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val description: String,
    val photo: Int,
    val rating: String,
    val price: String,
    val chip: String,
    val size: String,
    val storage: String
) : Parcelable
