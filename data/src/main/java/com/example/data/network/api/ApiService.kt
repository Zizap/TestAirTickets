package com.example.data.network.api

import com.example.data.network.models.offer.OfferResponse
import com.example.data.network.models.directFlights.DirectFlightsResponse
import com.example.data.network.models.tickets.TicketsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("uc?export=download&id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav")
    suspend fun getOffer(): OfferResponse

    @GET("uc?export=download&id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta")
    suspend fun getDirectFlights(): DirectFlightsResponse

    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getTickets(): TicketsResponse
}
