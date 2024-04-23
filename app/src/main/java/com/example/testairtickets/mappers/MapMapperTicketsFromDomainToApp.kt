package com.example.testairtickets.mappers

import com.example.data.network.models.tickets.HandLuggageModel
import com.example.data.network.models.tickets.LuggagePriceModel
import com.example.data.network.models.tickets.TicketsModel
import com.example.domain.models.tickets.FlightDetailsDomain
import com.example.domain.models.tickets.HandLuggageDomain
import com.example.domain.models.tickets.LuggageDomain
import com.example.domain.models.tickets.LuggagePriceDomain
import com.example.domain.models.tickets.TicketPriceDomain
import com.example.domain.models.tickets.TicketsDomain
import com.example.testairtickets.models.tickets.FlightDetails
import com.example.testairtickets.models.tickets.HandLuggage
import com.example.testairtickets.models.tickets.Luggage
import com.example.testairtickets.models.tickets.LuggagePrice
import com.example.testairtickets.models.tickets.TicketPrice
import com.example.testairtickets.models.tickets.Tickets


object MapMapperTicketsFromDomainToApp {
    fun mapToApp(ticketsDomain: TicketsDomain): Tickets {
        return Tickets(
            id = ticketsDomain.id,
            badge = ticketsDomain.badge,
            price = mapPriceToApp(ticketsDomain.price),
            providerName = ticketsDomain.providerName,
            company = ticketsDomain.company,
            departure = mapFlightDetailsToApp(ticketsDomain.departure),
            arrival = mapFlightDetailsToApp(ticketsDomain.arrival),
            hasTransfer = ticketsDomain.hasTransfer,
            hasVisaTransfer = ticketsDomain.hasVisaTransfer,
            luggage = ticketsDomain.luggage?.let { mapLuggageToApp(it) },
            handLuggage = mapHandLuggageToApp(ticketsDomain.handLuggage),
            isReturnable = ticketsDomain.isReturnable,
            isExchangable = ticketsDomain.isExchangable
        )
    }

    private fun mapPriceToApp(ticketPrice: TicketPriceDomain): TicketPrice {
        return TicketPrice(
            value = ticketPrice.value
        )
    }

    private fun mapFlightDetailsToApp(flightDetails: FlightDetailsDomain): FlightDetails {
        return FlightDetails(
            town = flightDetails.town,
            date = flightDetails.date,
            airport = flightDetails.airport
        )
    }

    private fun mapLuggageToApp(luggage: LuggageDomain): Luggage {
        return Luggage(
            hasLuggage = luggage.hasLuggage,
            price = mapLuggagePriceToApp(luggage.price)
        )
    }

    private fun mapLuggagePriceToApp(luggagePriceDomain: LuggagePriceDomain?): LuggagePrice {
        return LuggagePrice(
            value = luggagePriceDomain?.value
        )
    }

    private fun mapHandLuggageToApp(handLuggage: HandLuggageDomain): HandLuggage {
        return HandLuggage(
            hasHandLuggage = handLuggage.hasHandLuggage,
            size = handLuggage.size
        )
    }
}

fun TicketsDomain.mapToApp(): Tickets {
    return MapMapperTicketsFromDomainToApp.mapToApp(this)
}