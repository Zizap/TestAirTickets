package com.example.domain.models.directFlights

data class DirectFlightsDomain (
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: DirectFlightsPriceDomain
)