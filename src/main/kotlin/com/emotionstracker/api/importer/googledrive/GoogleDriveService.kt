package com.emotionstracker.api.importer.googledrive

import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GoogleDriveService(val googleDriveCredentialsProvider: GoogleDriveCredentialsProvider) {

    @Value("\${spring.application.name}")
    private lateinit var applicationName: String

    private val jsonFactory: JsonFactory = JacksonFactory.getDefaultInstance()

    fun getDrive(httpTransport: NetHttpTransport): Drive {
        return Drive.Builder(httpTransport, jsonFactory, googleDriveCredentialsProvider.provideCredentials(httpTransport))
                .setApplicationName(applicationName)
                .build()
    }
}
