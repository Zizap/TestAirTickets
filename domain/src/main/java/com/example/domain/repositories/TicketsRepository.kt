package com.example.domain.repositories

import com.example.domain.models.Either
import com.example.domain.models.tickets.TicketsDomain

interface TicketsRepository {

    suspend fun loadTickets(): Either<List<TicketsDomain>, Exception>

}