package com.example.data.network.models.tickets

import com.google.gson.annotations.SerializedName

data class TicketPriceModel(
    @SerializedName("value")
    val value: Int
)