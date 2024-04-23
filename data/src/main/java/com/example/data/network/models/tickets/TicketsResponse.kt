package com.example.data.network.models.tickets

import com.google.gson.annotations.SerializedName

data class TicketsResponse (
    @SerializedName("tickets")
    val response: List<TicketsModel>
)