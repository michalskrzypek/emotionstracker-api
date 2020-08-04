package com.emotionstracker.api.importer.csv

import com.emotionstracker.api.exceptions.InvalidCsvException

abstract class CsvConverter<T> {

    fun convertIfApplies(csv: Csv): List<T> = if (applies(csv)) convert(csv) else listOf()

    private fun applies(csv: Csv): Boolean = getHeaders() == csv.getHeaders().toString()

    @Throws(InvalidCsvException::class)
    protected abstract fun convert(csv: Csv): List<T>

    protected abstract fun getHeaders(): String
}
