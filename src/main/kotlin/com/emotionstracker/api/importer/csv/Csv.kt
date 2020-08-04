package com.emotionstracker.api.importer.csv

class Csv {

    private val rows = mutableListOf<CsvRow>()

    fun add(row: CsvRow) = rows.add(row)

    fun getHeaders(): CsvRow = rows[0]

    fun getRows(): List<CsvRow> = rows.subList(1, rows.size)

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        rows.forEach { stringBuilder.append("$it,\n") }
        return stringBuilder.toString()
    }
}
