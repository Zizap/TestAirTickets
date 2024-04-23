package com.example.data.network.models.tickets

import com.google.gson.annotations.SerializedName

data class FlightDetailsModel(
    @SerializedName("town")
    val town: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("airport")
    val airport: String
)