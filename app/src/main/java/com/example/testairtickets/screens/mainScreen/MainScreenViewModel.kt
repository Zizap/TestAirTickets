package com.example.testairtickets.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Either
import com.example.domain.usecase.OfferUseCase
import com.example.domain.usecase.PreferencesUseCase
import com.example.testairtickets.mappers.mapToApp
import com.example.testairtickets.models.offer.Offer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainScreenViewModel(
    private val offerUseCase: OfferUseCase,
    private val preferencesUseCase: PreferencesUseCase
) : ViewModel() {

    private val _mainScreenUiState = MutableStateFlow(
        MainScreenUiState(
            offer = emptyList()
        )
    )

    init {
        loadOffer()
        loadPrefs(key = KEY, defaultValue = DEFAULT)
    }

    val mainScreenUiState: StateFlow<MainScreenUiState> = _mainScreenUiState

    fun savePrefs(value: String) {
        preferencesUseCase.saveString(key = KEY, value = value)
    }

    private fun loadPrefs(key: String, defaultValue: String) {
        _mainScreenUiState.update {
            it.copy(
                city = preferencesUseCase.getString(key = key, defaultValue = defaultValue)
            )
        }
    }

    private fun loadOffer() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val either = offerUseCase.loadOffer()) {
                is Either.Success -> {
                    _mainScreenUiState.update {
                        it.copy(
                            offer = either.value.map { offerDomain ->
                                offerDomain.mapToApp()
                            },
                            error = null
                        )
                    }
                }

                is Either.Failure -> {
                    if (either.error is UnknownHostException) {
                        _mainScreenUiState.update { uiState ->
                            uiState.copy(
                                offer = offerUseCase.loadOfferFromDB().map { offerDomain ->
                                    offerDomain.mapToApp()
                                },
                                error = "Нет интернета"
                            )
                        }
                    } else {
                        _mainScreenUiState.update { uiState ->
                            uiState.copy(
                                offer = offerUseCase.loadOfferFromDB().map { offerDomain ->
                                    offerDomain.mapToApp()
                                },
                                error = "Неизвестная ошибка"
                            )
                        }
                    }
                }
            }
        }

    }
    companion object {
        const val KEY = "CITY"
        const val DEFAULT = "Москва"
    }
}

data class MainScreenUiState(
    val offer: List<Offer>,
    val city: String? = null,
    val error: String? = null
)