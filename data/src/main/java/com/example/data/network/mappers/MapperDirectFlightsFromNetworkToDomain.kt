package com.example.data.network.mappers

import com.example.data.network.models.directFlights.DirectFlightsModel
import com.example.data.network.models.directFlights.DirectFlightsPriceModel
import com.example.domain.models.directFlights.DirectFlightsDomain
import com.example.domain.models.directFlights.DirectFlightsPriceDomain


object MapperDirectFlightsFromNetworkToDomain {
    fun mapToDomainForData(directFlightsModel: DirectFlightsModel): DirectFlightsDomain {
        return DirectFlightsDomain(
            id = directFlightsModel.id,
            title = directFlightsModel.title,
            timeRange = directFlightsModel.timeRange,
            price = mapPriceToDomain(directFlightsModel.price)
        )
    }

    private fun mapPriceToDomain(directFlightsPriceModel: DirectFlightsPriceModel): DirectFlightsPriceDomain {
        return DirectFlightsPriceDomain(
            value = directFlightsPriceModel.value
        )
    }
}

fun DirectFlightsModel.mapToDomain(): DirectFlightsDomain {
    return MapperDirectFlightsFromNetworkToDomain.mapToDomainForData(this)
}