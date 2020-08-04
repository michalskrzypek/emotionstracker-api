package com.emotionstracker.api.importer.googledrive

import com.emotionstracker.api.importer.csv.Csv
import com.emotionstracker.api.importer.csv.CsvRow
import com.emotionstracker.api.logging.LoggerUtil
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.FileContent
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.drive.model.File
import com.google.api.services.drive.model.FileList
import org.springframework.stereotype.Service
import java.io.BufferedReader


@Service
class GoogleDriveClient(val googleDriveService: GoogleDriveService) {

    companion object {
        val log = LoggerUtil.logger(GoogleDriveClient.javaClass)
    }

    fun listFiles(): List<File> {
        val drive = googleDriveService.getDrive(newHttpTransport())
        val result: FileList = drive.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute()
        val files: List<File> = result.files
        if (files.isEmpty()) {
            log.info("No files found.")
        } else {
            log.info("Files:")
            files.forEach { log.info("${it.name} (${it.id})") }
        }
        return files
    }

    fun getFile(fileId: String): String {
        val drive = googleDriveService.getDrive(newHttpTransport())
        val result = drive.files().get(fileId).set("alt", "media").executeAsInputStream()
        val reader = BufferedReader(result.reader())
        var line = reader.readLine()
        var csv = Csv()
        while (line != null) {
            log.info(line)
            val row = CsvRow(line)
            csv.add(row)
            line = reader.readLine()
        }
        reader.close()
        return csv.toString()
    }

    // TODO remove method after testing Google Drive API
    fun addFile() {
        val resource = GoogleDriveClient::class.java.getResource("/mskrzypek97__at__gmail__com-12072020.csv")
        val filePath = java.io.File(resource.file)
        val mediaContent = FileContent("text/csv", filePath)

        val fileMetadata = File()
        fileMetadata.name = "test2.csv"
        val drive = googleDriveService.getDrive(newHttpTransport())

        val file: File = drive.files().create(fileMetadata, mediaContent)
                .execute()
        log.info("File ID: ${file.id}")
    }

    private fun newHttpTransport(): NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()
}
