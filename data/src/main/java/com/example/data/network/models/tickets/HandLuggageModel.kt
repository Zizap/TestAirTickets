package com.example.data.network.models.tickets

import com.google.gson.annotations.SerializedName

data class HandLuggageModel(
    @SerializedName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    @SerializedName("size")
    val size: String?
)