package com.example.domain.models.tickets


data class TicketsDomain(
    val id: Int,
    val badge: String?,
    val price: TicketPriceDomain,
    val providerName: String,
    val company: String,
    val departure: FlightDetailsDomain,
    val arrival: FlightDetailsDomain,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: LuggageDomain?,
    val handLuggage: HandLuggageDomain,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)