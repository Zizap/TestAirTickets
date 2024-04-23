package com.example.data.network.models.directFlights

import com.google.gson.annotations.SerializedName

data class DirectFlightsPriceModel(
    @SerializedName("value")
    val value: Int
)