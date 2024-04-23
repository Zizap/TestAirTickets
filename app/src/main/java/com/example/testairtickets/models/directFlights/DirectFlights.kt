package com.example.testairtickets.models.directFlights

data class DirectFlights (
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: DirectFlightsPrice
)