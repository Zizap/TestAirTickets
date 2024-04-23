package com.example.data.network.mappers

import com.example.data.network.models.tickets.FlightDetailsModel
import com.example.data.network.models.tickets.HandLuggageModel
import com.example.data.network.models.tickets.LuggageModel
import com.example.data.network.models.tickets.LuggagePriceModel
import com.example.data.network.models.tickets.TicketPriceModel
import com.example.data.network.models.tickets.TicketsModel
import com.example.domain.models.tickets.FlightDetailsDomain
import com.example.domain.models.tickets.HandLuggageDomain
import com.example.domain.models.tickets.LuggageDomain
import com.example.domain.models.tickets.LuggagePriceDomain
import com.example.domain.models.tickets.TicketPriceDomain
import com.example.domain.models.tickets.TicketsDomain


object MapperTicketsFromNetworkToDomain {
    fun mapToDomain(ticketModel: TicketsModel): TicketsDomain {
        return TicketsDomain(
            id = ticketModel.id,
            badge = ticketModel.badge,
            price = mapPriceToDomain(ticketModel.price),
            providerName = ticketModel.providerName,
            company = ticketModel.company,
            departure = mapFlightDetailsToDomain(ticketModel.departure),
            arrival = mapFlightDetailsToDomain(ticketModel.arrival),
            hasTransfer = ticketModel.hasTransfer,
            hasVisaTransfer = ticketModel.hasVisaTransfer,
            luggage = ticketModel.luggage?.let { mapLuggageToDomain(it) },
            handLuggage = mapHandLuggageToDomain(ticketModel.handLuggage),
            isReturnable = ticketModel.isReturnable,
            isExchangable = ticketModel.isExchangable
        )
    }

    private fun mapPriceToDomain(priceModel: TicketPriceModel): TicketPriceDomain {
        return TicketPriceDomain(
            value = priceModel.value
        )
    }

    private fun mapFlightDetailsToDomain(flightDetailsModel: FlightDetailsModel): FlightDetailsDomain {
        return FlightDetailsDomain(
            town = flightDetailsModel.town,
            date = flightDetailsModel.date,
            airport = flightDetailsModel.airport
        )
    }

    private fun mapLuggageToDomain(luggageModel: LuggageModel): LuggageDomain {
        return LuggageDomain(
            hasLuggage = luggageModel.hasLuggage,
            price = mapLuggagePriceToDomain(luggageModel.price)
        )
    }

    private fun mapLuggagePriceToDomain(luggagePriceModel: LuggagePriceModel?): LuggagePriceDomain {
        return LuggagePriceDomain(
            value = luggagePriceModel?.value
        )
    }

    private fun mapHandLuggageToDomain(handLuggageModel: HandLuggageModel): HandLuggageDomain {
        return HandLuggageDomain(
            hasHandLuggage = handLuggageModel.hasHandLuggage,
            size = handLuggageModel.size
        )
    }
}

fun TicketsModel.mapToDomain(): TicketsDomain {
    return MapperTicketsFromNetworkToDomain.mapToDomain(this)
}