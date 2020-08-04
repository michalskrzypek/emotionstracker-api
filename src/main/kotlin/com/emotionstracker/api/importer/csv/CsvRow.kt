package com.emotionstracker.api.importer.csv

data class CsvRow(val values: List<String>) {

    constructor(line: String) : this(line.split(CSV_DELIMITER))

    companion object {
        const val CSV_DELIMITER = ","
    }

    fun getValue(index: Int): String? {
        var value: String? = null
        if (values.size > index) {
            value = values[index]
        }
        return value
    }

    override fun toString(): String {
        return values.joinToString(CSV_DELIMITER)
    }
}
