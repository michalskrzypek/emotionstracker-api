package com.emotionstracker.api.importer.googledrive

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.DriveScopes
import org.springframework.stereotype.Service
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


@Service
class GoogleDriveCredentialsProvider {

    // TODO change scope to READ_ONLY after testing Google Drive API
    private val credentialsScope = listOf(DriveScopes.DRIVE)
    private val credentialsFilePath = "/credentials.json"

    fun provideCredentials(httpTransport: NetHttpTransport): Credential =
            GoogleCredential
                    .fromStream(
                            credentialsAsInputStream(),
                            httpTransport,
                            JacksonFactory.getDefaultInstance()
                    )
                    .createScoped(credentialsScope)

    @Throws(IOException::class)
    private fun credentialsAsInputStream(): InputStream = GoogleDriveCredentialsProvider::class.java.getResourceAsStream(credentialsFilePath)
            ?: throw FileNotFoundException("Resource not found: $credentialsFilePath")
}
