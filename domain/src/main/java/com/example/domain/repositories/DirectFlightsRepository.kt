package com.example.domain.repositories

import com.example.domain.models.Either
import com.example.domain.models.directFlights.DirectFlightsDomain

interface DirectFlightsRepository {

    suspend fun loadDirectFlights(): Either<List<DirectFlightsDomain>, Exception>

}