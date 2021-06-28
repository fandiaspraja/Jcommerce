package com.fandiaspraja.sehatqcommerce.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
        val loved: Int,
        val price: String,
        val imageUrl: String,
        val description: String,
        val id: String,
        val title: String,
        var isFavorite: Boolean,
        var isBuy: Boolean
): Parcelable