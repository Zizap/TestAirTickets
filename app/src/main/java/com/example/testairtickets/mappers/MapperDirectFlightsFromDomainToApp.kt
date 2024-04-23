package com.example.testairtickets.mappers

import com.example.domain.models.directFlights.DirectFlightsDomain
import com.example.domain.models.directFlights.DirectFlightsPriceDomain
import com.example.testairtickets.models.directFlights.DirectFlights
import com.example.testairtickets.models.directFlights.DirectFlightsPrice


object MapperDirectFlightsFromDomainToApp {
    fun mapToAppForDomain(directFlightsDomain: DirectFlightsDomain): DirectFlights {
        return DirectFlights(
            id = directFlightsDomain.id,
            title = directFlightsDomain.title,
            timeRange = directFlightsDomain.timeRange,
            price = mapPriceToApp(directFlightsDomain.price)
        )
    }

    private fun mapPriceToApp(directFlightsPriceDomain: DirectFlightsPriceDomain): DirectFlightsPrice {
        return DirectFlightsPrice(
            value = directFlightsPriceDomain.value
        )
    }
}

fun DirectFlightsDomain.mapToApp(): DirectFlights {
    return MapperDirectFlightsFromDomainToApp.mapToAppForDomain(this)
}