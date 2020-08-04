package com.emotionstracker.api.importer

import com.emotionstracker.api.domain.EmotionRecord
import com.emotionstracker.api.importer.csv.Csv
import com.emotionstracker.api.importer.csv.converters.EmotionRecordsCsvConverter
import com.emotionstracker.api.importer.googledrive.GoogleDriveClient
import com.emotionstracker.api.service.EmotionRecordService
import org.springframework.stereotype.Service

@Service
class EmotionRecordsImporter(
        private val googleDriveClient: GoogleDriveClient,
        private val emotionRecordService: EmotionRecordService,
        private val emotionRecordsCsvConverter: EmotionRecordsCsvConverter
) : Importer {

    override fun import() {
        val files = googleDriveClient.listFiles()
        files.forEach { import(it.id) }
    }

    private fun import(fileId: String) {
        val file: Csv = googleDriveClient.getFile(fileId)
        val records: List<EmotionRecord> = emotionRecordsCsvConverter.convertIfApplies(file)
        records.forEach { emotionRecordService.save(it) }
    }
}
