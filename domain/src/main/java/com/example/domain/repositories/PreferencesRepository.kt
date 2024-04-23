package com.example.domain.repositories

interface PreferencesRepository {

    fun saveString(key: String, value: String)

    fun getString(key: String, defaultValue: String): String?

}