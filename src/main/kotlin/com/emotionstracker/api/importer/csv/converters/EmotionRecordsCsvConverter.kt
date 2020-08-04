package com.emotionstracker.api.importer.csv.converters

import com.emotionstracker.api.domain.EmotionRecord
import com.emotionstracker.api.domain.EmotionType
import com.emotionstracker.api.domain.User
import com.emotionstracker.api.exceptions.InvalidCsvException
import com.emotionstracker.api.exceptions.InvalidCsvRowException
import com.emotionstracker.api.exceptions.UserNotFoundException
import com.emotionstracker.api.importer.csv.Csv
import com.emotionstracker.api.importer.csv.CsvConverter
import com.emotionstracker.api.importer.csv.CsvRow
import com.emotionstracker.api.service.UserService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class EmotionRecordsCsvConverter(private val userService: UserService) : CsvConverter<EmotionRecord>() {

    companion object {
        const val HEADERS = "email,name,date,emotion,count"
    }

    override fun getHeaders(): String = HEADERS

    override fun convert(csv: Csv): List<EmotionRecord> {
        try {
            return csv.getRows()
                    .map { mapCsvRowToEmotionRecord(it) }
        } catch (e: InvalidCsvRowException) {
            throw InvalidCsvException("Could not map from $csv to a list of EmotionRecords.")
        }
    }

    @Throws(InvalidCsvRowException::class)
    private fun mapCsvRowToEmotionRecord(csvRow: CsvRow): EmotionRecord {
        try {
            val count = csvRow.getValue(4).toLong()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val date = LocalDate.parse(csvRow.getValue(2), formatter)
            val emotion = EmotionType.valueOf(csvRow.getValue(3))
            val email = csvRow.getValue(0)
            val name = csvRow.getValue(1)

            var user: User
            try {
                user = userService.getByEmail(email)
            } catch (e: UserNotFoundException) {
                user = userService.save(User(email, name))
            }
            return EmotionRecord(count, date, emotion, user)
        } catch (e: IllegalArgumentException) {
            throw InvalidCsvRowException("Could not map from $csvRow to EmotionRecord.")
        }
    }
}
