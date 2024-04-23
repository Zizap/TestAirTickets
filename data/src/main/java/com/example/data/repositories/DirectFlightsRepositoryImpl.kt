package com.example.data.repositories

import com.example.data.network.apiDataSource.ApiDataSource
import com.example.data.network.mappers.mapToDomain
import com.example.domain.models.Either
import com.example.domain.models.directFlights.DirectFlightsDomain
import com.example.domain.repositories.DirectFlightsRepository

class DirectFlightsRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : DirectFlightsRepository {
    override suspend fun loadDirectFlights(): Either<List<DirectFlightsDomain>, Exception> {

        return try {
            when (val result = apiDataSource.loadDirectFlights()) {
                is Either.Success -> {
                    val directFlights = result.value.map { directFlights ->
                        directFlights.mapToDomain()
                    }
                    Either.Success(directFlights)
                }

                is Either.Failure -> {
                    Either.Failure(result.error)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Failure(e)
        }

    }
}