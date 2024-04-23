package com.example.data.network.models.offer

import com.google.gson.annotations.SerializedName

data class OfferResponse (
    @SerializedName("offers")
    val response: List<OfferModel>
)