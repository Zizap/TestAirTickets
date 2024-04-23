package com.example.data.network.models.offer

import com.google.gson.annotations.SerializedName


data class OfferModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("town")
    val town: String,
    @SerializedName("price")
    val price: PriceModel
)