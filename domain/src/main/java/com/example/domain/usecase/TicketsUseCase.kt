package com.example.domain.usecase

import com.example.domain.models.Either
import com.example.domain.models.tickets.TicketsDomain
import com.example.domain.repositories.TicketsRepository

class TicketsUseCase(
    private val ticketsRepository: TicketsRepository
) {
    suspend fun loadTickets(): Either<List<TicketsDomain>, Exception> {
        return ticketsRepository.loadTickets()
    }
}