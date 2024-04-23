package com.example.testairtickets.util

import android.text.InputFilter
import android.text.Spanned

class CyrillicInputFilter : InputFilter {
    private val cyrillicRegex = Regex("[а-яА-Я\\s\\-]+")

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if (source != null && !cyrillicRegex.matches(source)) {
            return ""
        }
        return null
    }
}