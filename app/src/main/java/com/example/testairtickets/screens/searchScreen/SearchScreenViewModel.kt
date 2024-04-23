package com.example.testairtickets.screens.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Either
import com.example.domain.usecase.DirectFlightsUseCase
import com.example.testairtickets.mappers.mapToApp
import com.example.testairtickets.models.directFlights.DirectFlights
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class SearchScreenViewModel(
    private val directFlightsUseCase: DirectFlightsUseCase
) : ViewModel() {

    private val _searchScreenUiState = MutableStateFlow(
        SearchScreenUiState(
            directFlights = emptyList()
        )
    )

    init {
        loadDirectFlights()
    }

    val searchScreenUiState: StateFlow<SearchScreenUiState> = _searchScreenUiState

    private fun loadDirectFlights() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val either = directFlightsUseCase.loadDirectFlights()) {
                is Either.Success -> {
                    _searchScreenUiState.update {
                        it.copy(
                            directFlights = either.value.map { directFlight ->
                                directFlight.mapToApp()
                            },
                            error = null
                        )
                    }
                }

                is Either.Failure -> {
                    if (either.error is UnknownHostException) {
                        _searchScreenUiState.update {
                            it.copy(
                                error = "Нет интернета",
                            )
                        }
                    } else {
                        _searchScreenUiState.update {
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

    data class SearchScreenUiState(
        val directFlights: List<DirectFlights>,
        val error: String? = null
    )