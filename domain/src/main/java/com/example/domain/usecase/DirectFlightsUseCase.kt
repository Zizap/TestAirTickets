package com.example.domain.usecase

import com.example.domain.models.Either
import com.example.domain.models.directFlights.DirectFlightsDomain
import com.example.domain.repositories.DirectFlightsRepository

class DirectFlightsUseCase(
    private val directFlightsRepository: DirectFlightsRepository
) {

    suspend fun loadDirectFlights(): Either<List<DirectFlightsDomain>, Exception> {
        return directFlightsRepository.loadDirectFlights()
    }

}