package com.example.domain.usecase

import com.example.domain.repositories.PreferencesRepository

class PreferencesUseCase(
    private val preferencesRepository: PreferencesRepository
) {
    fun saveString(key: String, value: String) {
        preferencesRepository.saveString(key = key, value = value)
    }

    fun getString(key: String, defaultValue: String): String? {
        return preferencesRepository.getString(key = key, defaultValue = defaultValue)
    }
}