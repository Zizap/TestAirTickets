package com.example.data.network.apiDataSource

import com.example.data.network.models.offer.OfferModel
import com.example.data.network.models.directFlights.DirectFlightsModel
import com.example.data.network.models.tickets.TicketsModel
import com.example.domain.models.Either

interface ApiDataSource {

    suspend fun loadOffer(): Either<List<OfferModel>, Exception>

    suspend fun loadDirectFlights(): Either<List<DirectFlightsModel>, Exception>

    suspend fun loadTickets(): Either<List<TicketsModel>, Exception>

}