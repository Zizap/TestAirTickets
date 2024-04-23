package com.example.data.network.models.tickets

import com.google.gson.annotations.SerializedName

data class TicketsModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("badge")
    val badge: String?,
    @SerializedName("price")
    val price: TicketPriceModel,
    @SerializedName("provider_name")
    val providerName: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("departure")
    val departure: FlightDetailsModel,
    @SerializedName("arrival")
    val arrival: FlightDetailsModel,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    @SerializedName("luggage")
    val luggage: LuggageModel?,
    @SerializedName("hand_luggage")
    val handLuggage: HandLuggageModel,
    @SerializedName("is_returnable")
    val isReturnable: Boolean,
    @SerializedName("is_exchangable")
    val isExchangable: Boolean
)
