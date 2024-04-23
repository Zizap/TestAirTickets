package com.example.data.repositories

import com.example.data.network.apiDataSource.ApiDataSource
import com.example.data.network.mappers.mapToDomain
import com.example.domain.models.Either
import com.example.domain.models.tickets.TicketsDomain
import com.example.domain.repositories.TicketsRepository

class TicketsRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : TicketsRepository {
    override suspend fun loadTickets(): Either<List<TicketsDomain>, Exception> {

        return try {
            when (val result = apiDataSource.loadTickets()) {
                is Either.Success -> {
                    val ticketsList = result.value.map { ticketsModel ->
                        ticketsModel.mapToDomain()
                    }
                    Either.Success(ticketsList)
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