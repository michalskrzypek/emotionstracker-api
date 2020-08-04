package com.emotionstracker.api.importer.csv

data class CsvRow(val values: List<String>) {

    constructor(line: String) : this(line.split(CSV_DELIMITER))

    companion object {
        const val CSV_DELIMITER = ","
    }

    @Throws(IllegalArgumentException::class)
    fun getValue(index: Int): String {
        if (index >= values.size) {
            throw IllegalArgumentException("$index is greater than the max index (${this.values.size - 1}) of $this")
        }
        return values[index]
    }

    override fun toString(): String {
        return values.joinToString(CSV_DELIMITER)
    }
}
