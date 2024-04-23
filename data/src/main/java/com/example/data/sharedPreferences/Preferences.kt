package com.example.data.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repositories.PreferencesRepository

class Preferences(context: Context) : PreferencesRepository {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    override fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }
}