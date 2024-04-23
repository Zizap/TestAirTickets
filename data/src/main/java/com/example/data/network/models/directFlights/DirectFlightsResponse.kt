package com.example.data.network.models.directFlights

import com.google.gson.annotations.SerializedName

data class DirectFlightsResponse (
    @SerializedName("tickets_offers")
    val response: List<DirectFlightsModel>
)