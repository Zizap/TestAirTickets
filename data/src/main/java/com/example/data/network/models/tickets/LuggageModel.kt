package com.example.data.network.models.tickets

import com.google.gson.annotations.SerializedName

data class LuggageModel(
    @SerializedName("has_luggage")
    val hasLuggage: Boolean,
    @SerializedName("price")
    val price: LuggagePriceModel?
)