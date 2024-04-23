package com.example.data.network.models.directFlights

import com.google.gson.annotations.SerializedName


data class DirectFlightsModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    @SerializedName("price")
    val price: DirectFlightsPriceModel
)
