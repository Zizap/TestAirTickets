package com.example.testairtickets.screens.ticketsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Either
import com.example.domain.usecase.TicketsUseCase
import com.example.testairtickets.mappers.mapToApp
import com.example.testairtickets.models.tickets.Tickets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class TicketsScreenViewModel(
    private val ticketsUseCase: TicketsUseCase
) : ViewModel() {

    private val _ticketsScreenUiState = MutableStateFlow(
        TicketsScreenUiState(
            ticketsList = emptyList()
        )
    )

    init {
        loadDirectFlights()
    }

    val ticketsScreenUiState: StateFlow<TicketsScreenUiState> = _ticketsScreenUiState

    private fun loadDirectFlights() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val either = ticketsUseCase.loadTickets()) {
                is Either.Success -> {
                    _ticketsScreenUiState.update {
                        it.copy(
                            ticketsList = either.value.map { ticket ->
                                ticket.mapToApp()
                            },
                            error = null
                        )
                    }
                }

                is Either.Failure -> {
                    if (either.error is UnknownHostException) {
                        _ticketsScreenUiState.update {
                            it.copy(
                                error = "Нет интернета",
                            )
                        }
                    } else {
                        _ticketsScreenUiState.update {
                            it.copy(
                                error = "Неизвестная ошибка",
                            )
                        }
                    }
                }
            }
        }
    }
}

data class TicketsScreenUiState(
    val ticketsList: List<Tickets>,
    val error: String? = null
)