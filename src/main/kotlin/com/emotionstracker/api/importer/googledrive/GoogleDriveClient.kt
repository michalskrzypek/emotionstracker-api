package com.emotionstracker.api.importer.googledrive

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.FileContent
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.File
import com.google.api.services.drive.model.FileList
import org.springframework.stereotype.Service


@Service
class GoogleDriveClient(val googleDriveAuthProvider: GoogleDriveAuthProvider) {

    private val APPLICATION_NAME: String = "Emotions Tracker API"
    private val JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance()

    fun listFiles(): List<File> {
        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        val service = Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleDriveAuthProvider.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build()

        // Print the names and IDs for up to 10 files.
        val result: FileList = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute()
        val files: List<File> = result.files
        if (files.isEmpty()) {
            println("No files found.")
        } else {
            println("Files:")
            files.forEach { System.out.printf("%s (%s)\n", it.name, it.id) }
        }

        return files
    }

    fun addFile() {
        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        val service = Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleDriveAuthProvider.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build()

        val resource = GoogleDriveClient::class.java.getResource("/mskrzypek97__at__gmail__com-12072020.csv")
        val filePath = java.io.File(resource.file)
        val mediaContent = FileContent("text/csv", filePath)

        val fileMetadata = File()
        fileMetadata.name = "lol.csv"

        val file: File = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute()
        println("File ID: " + file.id)
    }
}
