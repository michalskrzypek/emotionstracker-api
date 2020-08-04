package com.emotionstracker.api.importer.csv

interface CsvConverter<T> {

    fun convertIfApplies(csv: Csv): List<T> = if (applies(csv)) convert(csv) else listOf()

    fun applies(csv: Csv): Boolean = getHeaders() == csv.getHeaders().toString()

    fun convert(csv: Csv): List<T>

    fun getHeaders(): String
}
