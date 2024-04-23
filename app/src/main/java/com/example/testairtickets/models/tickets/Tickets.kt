package com.example.testairtickets.models.tickets

import com.example.domain.models.tickets.FlightDetailsDomain
import com.example.domain.models.tickets.HandLuggageDomain
import com.example.domain.models.tickets.LuggageDomain
import com.example.domain.models.tickets.TicketPriceDomain

data class Tickets(
    val id: Int,
    val badge: String?,
    val price: TicketPrice,
    val providerName: String,
    val company: String,
    val departure: FlightDetails,
    val arrival: FlightDetails,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage?,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)