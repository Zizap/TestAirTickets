package com.example.data.network.models.offer

import com.google.gson.annotations.SerializedName

data class PriceModel(
    @SerializedName("value")
    val value: Int
)